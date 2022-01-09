package thangok.icommerce.ordermanager.external.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StockResponseDTO implements Serializable {

    private UUID orderId;

    private List<StockModifyDTO> stockModifyList;

    private Boolean isSuccess;

}
