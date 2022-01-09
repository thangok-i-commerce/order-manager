package thangok.icommerce.ordermanager.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import thangok.icommerce.ordermanager.aop.io.LogIO;
import thangok.icommerce.ordermanager.dto.OrderDTO;
import thangok.icommerce.ordermanager.enumerable.OrderStatus;
import thangok.icommerce.ordermanager.external.dto.PaymentRequestDTO;
import thangok.icommerce.ordermanager.external.dto.StockModifyDTO;
import thangok.icommerce.ordermanager.external.dto.StockRequestDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderOrchestrator {

    @Autowired
    @Qualifier("payment")
    private WebClient paymentClient;

    @Autowired
    @Qualifier("stock")
    private WebClient stockClient;

    @LogIO
    public Mono<OrderDTO> placeOrder(final OrderDTO orderDTO) {
        final List<Activity> flow = new ArrayList<>();

        StockRequestDTO stockRequestDTO = new StockRequestDTO();
        stockRequestDTO.setOrderId(orderDTO.getId());
        stockRequestDTO.setStockModifyList(new ArrayList<>());

        orderDTO.getProductList().stream().map(x -> {
            StockModifyDTO stockModifyDTO = new StockModifyDTO();
            stockModifyDTO.setProductId(x.getProductId());
            stockModifyDTO.setModifyQuantity(x.getCount());
            return stockModifyDTO;
        }).forEach(stockRequestDTO.getStockModifyList()::add);

        StockActivity stockActivity = new StockActivity(stockClient, stockRequestDTO);
        flow.add(stockActivity);

        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setPayAmount(new BigDecimal("1000.500"));
        paymentRequestDTO.setPaymentTransactionId(UUID.randomUUID());

        PaymentActivity paymentActivity = new PaymentActivity(paymentClient, paymentRequestDTO);
        flow.add(paymentActivity);

        return this.perform(flow, orderDTO).onErrorResume(ex -> this.rollback(flow, orderDTO));
    }

    @LogIO
    private Mono<OrderDTO> perform(final List<Activity> flow, final OrderDTO orderDTO) {
        return Flux.fromStream(flow::stream)
                .flatMap(Activity::perform)
                .handle((success, synchronousSink) -> {
                    if (success) {
                        synchronousSink.next(true);
                    } else {
                        synchronousSink.error(new Exception("Something went wrong!"));
                    }
                })
                .then(Mono.fromCallable(() -> {
                    orderDTO.setOrderStatus(OrderStatus.FULL_FILLED);
                    return orderDTO;
                }));
    }

    @LogIO
    private Mono<OrderDTO> rollback(final List<Activity> flow, final OrderDTO orderDTO) {
        return Flux.fromStream(flow::stream)
                .filter(wf -> wf.getStatus().equals(ActivityStatus.SUCCESS))
                .flatMap(Activity::rollback)
                .retry(3)
                .then(Mono.fromCallable(() -> {
                    orderDTO.setOrderStatus(OrderStatus.ABORTED);
                    return orderDTO;
                }));
    }
}
