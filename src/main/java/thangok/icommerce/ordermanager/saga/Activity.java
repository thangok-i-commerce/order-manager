package thangok.icommerce.ordermanager.saga;

import reactor.core.publisher.Mono;

public interface Activity {

    Mono<Boolean> perform();

    Mono<Boolean> rollback();

    ActivityStatus getStatus();
}
