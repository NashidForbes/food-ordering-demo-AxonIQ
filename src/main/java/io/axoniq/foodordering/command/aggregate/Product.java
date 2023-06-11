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
public class Product {
    @AggregateIdentifier
    private UUID productId;
    private HashMap<UUID, Integer> selectedProducts;

    public Product() {
    }

    @CommandHandler
    public Product(CreateProductCommand command) {
        UUID aggregateId = UUID.randomUUID();
        // register event after executing command
        AggregateLifecycle.apply(new ProductCreatedEvent(aggregateId, command.getName(), command.getPrice(), command.getQuantity()));
    }

    @CommandHandler
    public void handle(RemoveProductStockCommand command) throws ProductStockDeselectionException {
        UUID productId = command.getProductId();
        if (!selectedProducts.containsKey(productId)) {
            throw new ProductStockDeselectionException("Product not found in product stocks list" + productId);
        }
        // register event after executing command
        AggregateLifecycle.apply(new ProductStockRemovedEvent(productId, command.getQuantity()));
    }

    @CommandHandler
    public void handle(SelectProductStockCommand command) {
        // register event after executing command
        AggregateLifecycle.apply(new ProductStockSelectedEvent(command.getProductId(), command.getQuantity()));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        productId = event.getProductId();
        selectedProducts = new HashMap<>();
        Integer productStock = event.getQuantity();

        if (productStock < 0) {
            productStock = 1;
        }

        selectedProducts.put(productId, productStock);
    }

    @EventSourcingHandler
    public void on(ProductStockSelectedEvent event) {
        // Add new products to the stock
        selectedProducts.merge(event.getProductId(), event.getQuantity(), Integer::sum);
    }


}
