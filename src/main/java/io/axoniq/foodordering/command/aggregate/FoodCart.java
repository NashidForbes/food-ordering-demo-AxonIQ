package io.axoniq.foodordering.command.aggregate;

import io.axoniq.foodordering.coreapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.UUID;

@Aggregate
public class FoodCart {
    @AggregateIdentifier
    private UUID foodCartId;
    private HashMap<UUID,Integer> selectedProducts;

    public FoodCart() {
    }

    @CommandHandler
    public FoodCart(CreateFoodCartCommand command) {
        UUID aggregateID = UUID.randomUUID();
        // register event after executing command
        AggregateLifecycle.apply(new FoodCartCreatedEvent(aggregateID));
    }

    @CommandHandler
    public void handle(DeselectProductCommand command) throws ProductDeselectionException {
        UUID productId = command.getProductId();
        if(!selectedProducts.containsKey(productId)){
         throw new ProductDeselectionException("Exception: Product " + productId + " not found in selected products list");
        }
        // register event after executing command
        AggregateLifecycle.apply(new ProductDeselectedEvent(foodCartId, command.getProductId(),
                command.getQuantity()));
    }

    @CommandHandler
    public void handle(SelectProductCommand command) {
        // register event after executing command
        AggregateLifecycle.apply(new ProductSelectedEvent(foodCartId, command.getProductId(),
                command.getQuantity()));
    }

    @EventSourcingHandler
    public void on(FoodCartCreatedEvent event) {
        foodCartId = event.getFoodCartId();
        selectedProducts = new HashMap<>();
    }

    @EventSourcingHandler
    public void on(ProductSelectedEvent event) {
        selectedProducts.merge(event.getProductId(), event.getQuantity(), Integer::sum);
    }
}
