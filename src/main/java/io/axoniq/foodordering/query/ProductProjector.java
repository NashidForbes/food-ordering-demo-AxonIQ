package io.axoniq.foodordering.query;

import io.axoniq.foodordering.coreapi.*;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ProductProjector {

    private final ProductStockViewRepository repository;

    public ProductProjector(ProductStockViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductView view = new ProductView(event.getProductId(), event.getName(), Collections.emptyMap());
        repository.save(view);
    }

    public void on(ProductSelectedEvent event) {
        repository.findById(event.getProductId()).ifPresent(ProductView ->
                ProductView.addProducts(event.getProductId(),  event.getQuantity()));
    }

    public void on(ProductDeselectedEvent event) {
        repository.findById(event.getProductId()).ifPresent(ProductView ->
                ProductView.removeProducts(event.getProductId(), event.getQuantity()));
    }

    public void handle(FindProductQuery query) {
        repository.findById(query.getProductId()).orElse(null);
    }

}
