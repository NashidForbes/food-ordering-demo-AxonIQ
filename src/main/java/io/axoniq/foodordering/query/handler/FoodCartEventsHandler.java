package io.axoniq.foodordering.query.handler;

import io.axoniq.foodordering.coreapi.FoodCartCreatedEvent;
import io.axoniq.foodordering.coreapi.FoodCartAddProductEvent;
import io.axoniq.foodordering.coreapi.data.domain.FoodCartEntity;
import io.axoniq.foodordering.coreapi.data.domain.ProductEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.FoodCartRepository;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FoodCartEventsHandler {

    private final FoodCartRepository foodCartRepository;
    private final ProductsRepository productsRepository;

    public FoodCartEventsHandler(FoodCartRepository foodCartRepository, ProductsRepository productsRepository) {
        this.foodCartRepository = foodCartRepository;
        this.productsRepository = productsRepository;
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
        ProductEntity productEntity = productsRepository.findByProductId(event.getProductId());


        try {
            log.debug("ProductSelectedEvent: Current product quantity " + productEntity.getQuantity());

            if (!((productEntity.getQuantity() - event.getQuantity()) <= -1)) {
                Integer setRemainingProductStock = productEntity.getQuantity() - event.getQuantity();
                productEntity.setQuantity(setRemainingProductStock);

                productsRepository.save(productEntity);

                log.debug("ProductSelectedEvent: New product quantity " + productEntity.getQuantity());
            }

            boolean itemExists = foodCartEntity.getProducts().stream()
                    .anyMatch(product -> product.getProductId() == event.getProductId());

            if(!itemExists)
             foodCartEntity.getProducts().add(productEntity);

            foodCartRepository.save(foodCartEntity);

            log.info("ProductReservedEvent is called for productId:" + event.getProductId() +
                    " and for food cart: " + event.getFoodCartId());

        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
