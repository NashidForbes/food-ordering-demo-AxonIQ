package io.axoniq.foodordering.command.handler;

import io.axoniq.foodordering.coreapi.FoodCartCreatedEvent;
import io.axoniq.foodordering.coreapi.ProductCreatedEvent;
import io.axoniq.foodordering.coreapi.data.domain.FoodCartEntity;
import io.axoniq.foodordering.coreapi.data.domain.ProductEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.FoodCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ProcessingGroup("foodcart-group")
public class FoodCartEventsHandler {
    
    private final FoodCartRepository foodCartRepository;

    public FoodCartEventsHandler(FoodCartRepository foodCartRepository) {
        this.foodCartRepository = foodCartRepository;
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception{
        throw exception;
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    public void handle(IllegalArgumentException exception) {
        log.error(exception.getMessage());
    }

    @EventHandler
    public void on(FoodCartCreatedEvent event){
        FoodCartEntity foodCartEntity = new FoodCartEntity();
        BeanUtils.copyProperties(event, foodCartEntity);

        try {
            foodCartRepository.save(foodCartEntity);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    } 
}
