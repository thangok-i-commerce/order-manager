package thangok.icommerce.ordermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.dto.OrderDTO;
import thangok.icommerce.ordermanager.dto.OrderProductDTO;
import thangok.icommerce.ordermanager.entity.Order;
import thangok.icommerce.ordermanager.entity.OrderProduct;
import thangok.icommerce.ordermanager.enumerable.OrderStatus;
import thangok.icommerce.ordermanager.repository.OrderRepository;
import thangok.icommerce.ordermanager.saga.OrderOrchestrator;
import thangok.icommerce.ordermanager.service.OrderService;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderOrchestrator orderOrchestrator;

    @Override
    public Mono<OrderDTO> getOrderById(UUID orderId) {
        return null;
    }

    @Override
    public Flux<OrderDTO> getOrderByUser(UUID userId) {
        return null;
    }

    @Override
    public Mono<OrderDTO> placeOrder(OrderDTO orderDTO) {
        final Order order = new Order();

        order.setUserId(orderDTO.getUserId());

        order.setProductList(new ArrayList<>());
        orderDTO.getProductList().stream().map(x -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductId(x.getProductId());
            orderProduct.setCount(x.getCount());
            orderProduct.setOrder(order);
            return orderProduct;
        }).forEach(order.getProductList()::add);

        order.setOrderStatus(OrderStatus.FULL_FILLED);

        Order persistedOrder = orderRepository.save(order);

        OrderDTO result = new OrderDTO();

        result.setId(persistedOrder.getId());
        result.setUserId(persistedOrder.getUserId());
        result.setOrderStatus(persistedOrder.getOrderStatus());

        result.setProductList(new ArrayList<>());
        persistedOrder.getProductList().stream().map(y -> {
            OrderProductDTO orderProductDTO = new OrderProductDTO();
            orderProductDTO.setOrderId(y.getOrder().getId());
            orderProductDTO.setProductId(y.getProductId());
            orderProductDTO.setCount(y.getCount());
            return orderProductDTO;
        }).forEach(result.getProductList()::add);

        // Saga Orchestrator
        return orderOrchestrator.placeOrder(result);
    }
}
