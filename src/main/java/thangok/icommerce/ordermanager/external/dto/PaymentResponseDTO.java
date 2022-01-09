package thangok.icommerce.ordermanager.external.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaymentResponseDTO implements Serializable {

    private UUID paymentTransactionId;

    private BigDecimal payAmount;

    private Boolean isSuccess;

}
