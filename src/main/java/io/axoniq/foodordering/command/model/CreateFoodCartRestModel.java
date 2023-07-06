package io.axoniq.foodordering.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class CreateFoodCartRestModel {

    private final Map<String, Integer> products = new HashMap<>();

}
