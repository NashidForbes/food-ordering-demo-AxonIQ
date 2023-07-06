package io.axoniq.foodordering.command.aggregate;

import io.axoniq.foodordering.command.commands.AddProductToCartCommand;
import io.axoniq.foodordering.command.commands.CreateFoodCartCommand;
import io.axoniq.foodordering.command.commands.RemoveProductFromCartCommand;
import io.axoniq.foodordering.coreapi.FoodCartAddProductEvent;
import io.axoniq.foodordering.coreapi.FoodCartCreatedEvent;
import io.axoniq.foodordering.coreapi.FoodCartRemoveProductEvent;
import io.axoniq.foodordering.coreapi.ProductDeselectionException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Map;
import java.util.UUID;

@Aggregate
public class FoodCart {
    @AggregateIdentifier
    private String foodCartId;
    private Map<String, Integer> selectedProducts;

    public FoodCart() {
    }

    @CommandHandler
    public FoodCart(CreateFoodCartCommand command) {
        String aggregateID = UUID.randomUUID().toString();
        // register event after executing command
        AggregateLifecycle.apply(new FoodCartCreatedEvent(aggregateID, command.getProducts()));
    }

    @CommandHandler
    public void handle(RemoveProductFromCartCommand command) throws ProductDeselectionException {
        String productId = command.getProductId();

        if (!selectedProducts.containsKey(productId)) {
            throw new ProductDeselectionException("Exception: Product with product Id : " + productId + " not found in your cart");
        }
        // register event after executing command
        AggregateLifecycle.apply(new FoodCartRemoveProductEvent(command.getFoodCartId(), command.getProductId(),
                command.getQuantity()));
    }

    @CommandHandler
    public void handle(AddProductToCartCommand command) {
        // register event after executing command
        AggregateLifecycle.apply(new FoodCartAddProductEvent(command.getFoodCartId(), command.getProductId(),
                command.getQuantity()));
    }

    @EventSourcingHandler
    public void on(FoodCartCreatedEvent event) {
        foodCartId = event.getFoodCartId();
        selectedProducts = event.getProducts();
    }

    @EventSourcingHandler
    public void on(FoodCartAddProductEvent event) {
        foodCartId = event.getFoodCartId();
        selectedProducts.put(event.getProductId(), event.getQuantity());
    }
}
