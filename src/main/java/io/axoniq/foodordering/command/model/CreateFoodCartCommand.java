package io.axoniq.foodordering.command.model;

import io.axoniq.foodordering.query.model.ProductRestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;
import java.util.UUID;

@Builder
// @AllArgsConstructor makes the constructor public
@AllArgsConstructor
@Data
public class CreateFoodCartCommand {

    @TargetAggregateIdentifier
    private final UUID foodCartId;
    private final List<ProductRestModel> products;

}
