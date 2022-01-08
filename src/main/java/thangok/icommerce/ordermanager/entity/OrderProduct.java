package thangok.icommerce.ordermanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "order_product")
@Getter
@Setter
@NoArgsConstructor
public class OrderProduct implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "count")
    private Long count;

}
