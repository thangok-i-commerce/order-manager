package thangok.icommerce.ordermanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
public class Brand implements Serializable {

    @Id
    private String brandCode;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "description")
    private String description;

}
