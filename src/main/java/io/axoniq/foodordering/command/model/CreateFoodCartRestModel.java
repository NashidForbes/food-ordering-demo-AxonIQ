package io.axoniq.foodordering.command.model;

import io.axoniq.foodordering.query.model.ProductRestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@AllArgsConstructor
@Data
public class CreateFoodCartRestModel {

    private final Map<UUID, Integer> products = new HashMap<>();

}
