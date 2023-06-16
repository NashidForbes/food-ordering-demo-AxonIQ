package io.axoniq.foodordering.query.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodCartRestModelView  implements Serializable {
    private static final long serialVersionUID = -165060882983025837L;

    private UUID foodCartId;
    private List<ProductRestModel> products;

}
