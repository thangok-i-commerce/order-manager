package thangok.icommerce.ordermanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thangok.icommerce.ordermanager.enumerable.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> productList;
}
