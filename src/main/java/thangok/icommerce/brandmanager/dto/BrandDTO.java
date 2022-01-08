package thangok.icommerce.ordermanager.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class BrandDTO implements Serializable {

    private String brandCode;

    private String brandName;
    private String description;

}
