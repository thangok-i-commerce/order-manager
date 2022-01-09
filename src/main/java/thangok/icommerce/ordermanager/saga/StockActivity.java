package thangok.icommerce.ordermanager.saga;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.aop.io.LogIO;
import thangok.icommerce.ordermanager.external.dto.StockRequestDTO;
import thangok.icommerce.ordermanager.external.dto.StockResponseDTO;

@Slf4j
public class StockActivity implements Activity {

    String stockExportEndpoint = "/export";
    String stockImportEndpoint = "/import";

    WebClient webClient;

    StockRequestDTO requestDTO;

    private ActivityStatus activityStatus = ActivityStatus.PENDING;

    public StockActivity(WebClient webClient, StockRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @LogIO
    @Override
    public Mono<Boolean> perform() {
        return this.webClient
                .post()
                .uri(stockExportEndpoint)
                .body(BodyInserters.fromValue(this.requestDTO))
                .retrieve()
                .bodyToMono(StockResponseDTO.class)
                .map(StockResponseDTO::getIsSuccess)
                .doOnNext(x -> this.activityStatus = ActivityStatus.SUCCESS);
    }

    @LogIO
    @Override
    public Mono<Boolean> rollback() {
        log.info("perform rollback");
        return this.webClient
                .post()
                .uri(stockImportEndpoint)
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
