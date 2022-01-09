package thangok.icommerce.ordermanager.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.entity.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends R2dbcRepository<Order, UUID> {

    Mono<Order> findByUserId(UUID userId);

}
