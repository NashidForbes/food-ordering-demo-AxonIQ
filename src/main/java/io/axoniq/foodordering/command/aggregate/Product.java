package io.axoniq.foodordering.command.aggregate;

import io.axoniq.foodordering.coreapi.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Aggregate
public class Product {
    @AggregateIdentifier
    private UUID productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;

    public Product() {
    }

    @CommandHandler
    public Product(CreateProductCommand command){
        UUID aggregateId = UUID.randomUUID();
        // register event after executing command
        AggregateLifecycle.apply(new ProductCreatedEvent(aggregateId, command.getName(), command.getPrice(), command.getQuantity()));
    }

    @CommandHandler
    public void handle(RemoveProductStockCommand command) throws ProductStockDeselectionException {
        UUID productId = command.getProductId();
       /* if (!selectedProducts.containsKey(productId)) {
            throw new ProductStockDeselectionException("Product not found in product stocks list" + productId);
        }*/
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
        this.price = event.getPrice();
        this.title = event.getName();
        this.quantity = event.getQuantity();
        log.info("Product Created: " + productId);
    }

    @EventSourcingHandler
    public void on(FoodCartAddProductEvent event) {
        // Do something after the event happens
        this.quantity -= event.getQuantity();
    }


}
