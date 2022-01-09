package thangok.icommerce.ordermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.dto.OrderDTO;
import thangok.icommerce.ordermanager.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public Mono<OrderDTO> placeOrder(@RequestBody final OrderDTO orderDTO) {
        return orderService.placeOrder(orderDTO);
    }
}
