package thangok.icommerce.ordermanager.external.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StockModifyDTO implements Serializable {

    private Long productId;

    private Long modifyQuantity;

}
