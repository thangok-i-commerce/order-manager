package thangok.icommerce.ordermanager.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.dto.OrderDTO;

import java.util.UUID;

public interface OrderService {

    Mono<OrderDTO> getOrderById(UUID orderId);

    Flux<OrderDTO> getOrderByUser(UUID userId);

    Mono<OrderDTO> placeOrder(OrderDTO orderDTO);

}
