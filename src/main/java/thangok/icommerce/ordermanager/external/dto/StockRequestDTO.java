package thangok.icommerce.ordermanager.external.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StockRequestDTO implements Serializable {

    private UUID orderId;

    private List<StockModifyDTO> stockModifyList;

}
