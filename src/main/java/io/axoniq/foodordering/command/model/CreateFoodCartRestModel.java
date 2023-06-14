package io.axoniq.foodordering.command.model;

import io.axoniq.foodordering.query.model.ProductRestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class CreateFoodCartRestModel {

    private final List<ProductRestModel> products = new ArrayList<>();

}
