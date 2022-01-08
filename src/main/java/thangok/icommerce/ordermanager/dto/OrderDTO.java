package thangok.icommerce.ordermanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import thangok.icommerce.ordermanager.enumerable.OrderStatus;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDTO implements Serializable {

    private UUID id;

    private UUID userId;

    private OrderStatus orderStatus;

    private List<OrderProductDTO> productList;

}
