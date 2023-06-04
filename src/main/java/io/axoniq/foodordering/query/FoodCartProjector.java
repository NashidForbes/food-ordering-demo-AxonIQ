package io.axoniq.foodordering.query;

import io.axoniq.foodordering.coreapi.FindFoodCartQuery;
import io.axoniq.foodordering.coreapi.FoodCartCreatedEvent;
import io.axoniq.foodordering.coreapi.ProductSelectedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class FoodCartProjector {

    private final FoodCartViewRepository repository;

    public FoodCartProjector(FoodCartViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(FoodCartCreatedEvent event){
       FoodCartView foodCartView = new FoodCartView(event.getFoodCartId(), Collections.emptyMap());
       repository.save(foodCartView);
    }

    @EventHandler
    public void on(ProductSelectedEvent event){
        repository.findById(event.getProductId()).ifPresent(foodCartView ->
         foodCartView.addProducts(event.getProductId(), event.getQuantity());
        );
        repository.save(foodCartView);
    }


    @QueryHandler
    public FoodCartView handle(FindFoodCartQuery query){
        return repository.findById(query.getFoodCartId()).orElse(null);
    }
}
