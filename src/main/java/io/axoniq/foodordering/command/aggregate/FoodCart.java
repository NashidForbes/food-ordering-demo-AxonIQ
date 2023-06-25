package io.axoniq.foodordering.command.aggregate;

import io.axoniq.foodordering.command.commands.AddProductToCartCommand;
import io.axoniq.foodordering.command.commands.CreateFoodCartCommand;
import io.axoniq.foodordering.command.commands.RemoveProductFromCartCommand;
import io.axoniq.foodordering.coreapi.*;
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
    private Map<UUID,Integer> selectedProducts;

    public FoodCart() {
    }

    @CommandHandler
    public FoodCart(CreateFoodCartCommand command) {
        UUID aggregateID = UUID.randomUUID();
        // register event after executing command
        AggregateLifecycle.apply(new FoodCartCreatedEvent(aggregateID, command.getProducts()));
    }

    @CommandHandler
    public void handle(RemoveProductFromCartCommand command) throws ProductDeselectionException {
        UUID productId = command.getProductId();

        if (!selectedProducts.containsKey(productId)) {
            throw new ProductDeselectionException("Exception: Product with product Id : " + productId + " not found in your cart");
        }
        // register event after executing command
        AggregateLifecycle.apply(new FoodCartRemoveProductEvent(foodCartId, command.getProductId(),
                command.getQuantity()));
    }

    @CommandHandler
    public void handle(AddProductToCartCommand command) {
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
