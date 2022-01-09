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
import thangok.icommerce.ordermanager.repository.StockRepository;
import thangok.icommerce.ordermanager.service.OrderService;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    OrderRepository orderRepository;

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

        orderDTO.getProductList().stream().map(x -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductId(x.getProductId());
            orderProduct.setCount(x.getCount());
            orderProduct.setOrder(order);
            return orderProduct;
        }).forEach(order.getProductList()::add);

        order.setOrderStatus(OrderStatus.FULL_FILLED);

        return orderRepository.save(order).map(x -> {
            OrderDTO result = new OrderDTO();

            result.setId(x.getId());
            result.setUserId(x.getUserId());
            result.setOrderStatus(x.getOrderStatus());

            x.getProductList().stream().map(y -> {
                OrderProductDTO orderProductDTO = new OrderProductDTO();
                orderProductDTO.setOrderId(y.getOrder().getId());
                orderProductDTO.setProductId(y.getProductId());
                orderProductDTO.setCount(y.getCount());
                return orderProductDTO;
            }).forEach(result.getProductList()::add);

            return result;
        });
    }
}
