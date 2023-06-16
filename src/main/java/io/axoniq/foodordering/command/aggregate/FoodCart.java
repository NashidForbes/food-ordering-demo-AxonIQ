package io.axoniq.foodordering.command.aggregate;

import io.axoniq.foodordering.command.model.CreateFoodCartCommand;
import io.axoniq.foodordering.coreapi.*;
import io.axoniq.foodordering.query.model.ProductRestModel;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.*;

@Aggregate
public class FoodCart {
    @AggregateIdentifier
    private UUID foodCartId;
    private List<ProductRestModel> selectedProducts;

    public FoodCart() {
    }

    @CommandHandler
    public FoodCart(CreateFoodCartCommand command) {
        UUID aggregateID = UUID.randomUUID();
        // register event after executing command
        AggregateLifecycle.apply(new FoodCartCreatedEvent(aggregateID, command.getProducts()));
    }

    @CommandHandler
    public void handle(DeselectProductCommand command) throws ProductDeselectionException {
  /*        if(!selectedProducts.contains(selectedProducts.get(0).getProductId())){
         throw new ProductDeselectionException("Exception: Product " + productId + " not found in selected products list");
        }*/
        // register event after executing command
        AggregateLifecycle.apply(new ProductDeselectedEvent(foodCartId, command.getProductId(),
                command.getQuantity()));
    }

    @CommandHandler
    public void handle(SelectProductCommand command) {
        // register event after executing command
        AggregateLifecycle.apply(new FoodCartAddProductEvent(foodCartId, command.getProductId(),
                command.getQuantity()));
    }

    @EventSourcingHandler
    public void on(FoodCartCreatedEvent event) {
        foodCartId = event.getFoodCartId();
        selectedProducts = event.getProducts();
    }
}
