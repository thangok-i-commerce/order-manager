package thangok.icommerce.ordermanager.saga;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.external.dto.PaymentRequestDTO;
import thangok.icommerce.ordermanager.external.dto.PaymentResponseDTO;

public class PaymentActivity implements Activity {

    String paymentPurchaseEndpoint = "/purchase";
    String paymentRefundEndpoint = "/refund";

    WebClient webClient;

    PaymentRequestDTO requestDTO;

    ActivityStatus activityStatus = ActivityStatus.PENDING;

    public PaymentActivity(WebClient webClient, PaymentRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public Mono<Boolean> perform() {
        return this.webClient
                .post()
                .uri(paymentPurchaseEndpoint)
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(PaymentResponseDTO.class)
                .map(PaymentResponseDTO::getIsSuccess)
                .doOnNext(x -> this.activityStatus = x ? ActivityStatus.SUCCESS : ActivityStatus.FAIL);
    }

    @Override
    public Mono<Boolean> rollback() {
        return this.webClient
                .post()
                .uri(paymentRefundEndpoint)
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(Void.class)
                .map(x -> true)
                .onErrorReturn(false);
    }

    @Override
    public ActivityStatus getStatus() {
        return this.activityStatus;
    }
}
