package thangok.icommerce.ordermanager.external.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StockModifyDTO implements Serializable {

    private Long productId;

    private Long modifyQuantity;

}
