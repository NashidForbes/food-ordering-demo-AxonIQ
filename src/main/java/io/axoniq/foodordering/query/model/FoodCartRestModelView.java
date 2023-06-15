package io.axoniq.foodordering.query.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodCartRestModelView {
    private UUID foodCartId;
    private List<ProductRestModel> products;

}
