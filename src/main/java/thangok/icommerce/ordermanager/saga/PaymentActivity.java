package thangok.icommerce.ordermanager.saga;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.aop.io.LogIO;
import thangok.icommerce.ordermanager.external.dto.PaymentRequestDTO;
import thangok.icommerce.ordermanager.external.dto.PaymentResponseDTO;

@Slf4j
public class PaymentActivity implements Activity {

    String paymentPurchaseEndpoint = "/purchase";
    String paymentRefundEndpoint = "/refund";

    WebClient webClient;

    PaymentRequestDTO requestDTO;

    private ActivityStatus activityStatus = ActivityStatus.PENDING;

    public PaymentActivity(WebClient webClient, PaymentRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @LogIO
    @Override
    public Mono<Boolean> perform() {
        return this.webClient
                .post()
                .uri(paymentPurchaseEndpoint)
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(PaymentResponseDTO.class)
                .map(PaymentResponseDTO::getIsSuccess)
                .doOnNext(x -> {
                    if (x) {
                        this.activityStatus = ActivityStatus.SUCCESS;
                    } else {
                        this.activityStatus = ActivityStatus.FAIL;
                    }
                });
    }

    @LogIO
    @Override
    public Mono<Boolean> rollback() {
        log.info("perform rollback");
        return this.webClient
                .post()
                .uri(paymentRefundEndpoint)
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(Void.class)
                .map(x -> true)
                .onErrorReturn(false);
    }

    @LogIO
    @Override
    public ActivityStatus getStatus() {
        return this.activityStatus;
    }
}
