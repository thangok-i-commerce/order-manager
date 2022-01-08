package thangok.icommerce.ordermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.dto.OrderDTO;
import thangok.icommerce.ordermanager.entity.Stock;
import thangok.icommerce.ordermanager.service.OrderService;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    Stock stockRepository;


    @Override
    public Mono<OrderDTO> getOrderById(UUID orderId) {
        return null;
    }

    @Override
    public Flux<OrderDTO> getOrderByUser(UUID userId) {
        return null;
    }
}
