package thangok.icommerce.ordermanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderProductDTO {

    private UUID id;

    private UUID orderId;

    private Long productId;

    private Long count;

}
