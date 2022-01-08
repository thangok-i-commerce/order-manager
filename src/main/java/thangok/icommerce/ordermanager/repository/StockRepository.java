package thangok.icommerce.ordermanager.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.entity.Stock;

@Repository
public interface StockRepository extends R2dbcRepository<Stock, Long> {

    Mono<Stock> findByProductId(Long productId);

}
