package thangok.icommerce.ordermanager.saga;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.external.dto.StockRequestDTO;
import thangok.icommerce.ordermanager.external.dto.StockResponseDTO;

public class StockActivity implements Activity {

    String stockExportEndpoint = "/export";
    String stockImportEndpoint = "/import";

    WebClient webClient;

    StockRequestDTO requestDTO;

    ActivityStatus activityStatus = ActivityStatus.PENDING;

    public StockActivity(WebClient webClient, StockRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public Mono<Boolean> perform() {
        return this.webClient
                .post()
                .uri(stockExportEndpoint)
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(StockResponseDTO.class)
                .map(StockResponseDTO::getIsSuccess)
                .doOnNext(x -> this.activityStatus = x ? ActivityStatus.SUCCESS : ActivityStatus.FAIL);
    }

    @Override
    public Mono<Boolean> rollback() {
        return this.webClient
                .post()
                .uri(stockImportEndpoint)
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
