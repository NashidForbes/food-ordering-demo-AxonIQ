package io.axoniq.foodordering.query.handler;

import io.axoniq.foodordering.coreapi.FoodCartCreatedEvent;
import io.axoniq.foodordering.coreapi.FoodCartAddProductEvent;
import io.axoniq.foodordering.coreapi.FoodCartRemoveProductEvent;
import io.axoniq.foodordering.coreapi.data.domain.FoodCartEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.FoodCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class FoodCartEventsHandler {

    private final FoodCartRepository foodCartRepository;
    Map<UUID, Integer> productsInCart;

    public FoodCartEventsHandler(FoodCartRepository foodCartRepository) {
        this.foodCartRepository = foodCartRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) {
        log.error(exception.getMessage());
    }

    @EventHandler
    public void on(FoodCartCreatedEvent event) {
        FoodCartEntity foodCartEntity = new FoodCartEntity();
        BeanUtils.copyProperties(event, foodCartEntity);

        try {
            foodCartRepository.save(foodCartEntity);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void on(FoodCartAddProductEvent event) {
        FoodCartEntity foodCartEntity = foodCartRepository.findByFoodCartId(event.getFoodCartId());
        productsInCart = new HashMap<>();

        try {
            productsInCart = foodCartEntity.getProducts();

            if(productsInCart.containsKey(event.getProductId())) {
                productsInCart.put(event.getProductId(), productsInCart.get(event.getProductId()) + event.getQuantity());
            }

            foodCartEntity.setProducts(productsInCart);

            foodCartRepository.save(foodCartEntity);

            log.info("ProductReservedEvent is called for productId:" + event.getProductId() +
                    " and for food cart: " + event.getFoodCartId());

        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void on(FoodCartRemoveProductEvent event) {
        FoodCartEntity foodCartEntity = foodCartRepository.findByFoodCartId(event.getFoodCartId());
        productsInCart = new HashMap<>();

        try {
            productsInCart = foodCartEntity.getProducts();

            if((productsInCart.get(event.getProductId()) - event.getQuantity()) < 0){
                throw new IllegalArgumentException("Removing number of products " + event.getQuantity() +
                        " is greater than actual number of items in cart");
            }

            if(productsInCart.containsKey(event.getProductId())) {
                productsInCart.put(event.getProductId(), productsInCart.get(event.getProductId()) - event.getQuantity());
            }

            foodCartEntity.setProducts(productsInCart);

            foodCartRepository.save(foodCartEntity);

            log.info(event.getQuantity() + " items have been removed from Foodcart for productId:" + event.getProductId() +
                    " and from food cart: " + event.getFoodCartId());

        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
