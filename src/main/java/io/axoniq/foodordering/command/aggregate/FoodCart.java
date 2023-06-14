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
        UUID productId = command.getProductId();
        if(!selectedProducts.contains(selectedProducts.get(0).getProductId())){
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
        selectedProducts = event.getProducts();
    }

    @EventSourcingHandler
    public void on(ProductSelectedEvent event) {

        Map<UUID, Integer> mergedMap = new HashMap<>();

        // merged selected product quantity to hashmap then convert to list
        // can use hashmap for big O time complexity but for now just use foreach loop
        // for proof of concept
        for (ProductRestModel item : selectedProducts) {
            UUID key = item.getProductId();
            int quantity = (item.getQuantity() == null || item.getQuantity() < 0)? 0 : item.getQuantity();


            if(event.getProductId().equals(item.getProductId())){
                item.setQuantity(quantity + event.getQuantity());
            }

            // mergedMap.put(key, mergedMap.getOrDefault(key, 0) + quantity);
            // Update product quantity in selected products list
        }

        // confirm updated list
        // Printing the updated list
        for (ProductRestModel item : selectedProducts) {
            System.out.println(item.getName() + ": " + item.getQuantity());
        }

        //List<Map.Entry<UUID, Integer>> mergedList = new ArrayList<>(mergedMap.entrySet());
        //selectedProducts.merge(event.getProductId(), event.getQuantity(), Integer::sum);
    }
}
