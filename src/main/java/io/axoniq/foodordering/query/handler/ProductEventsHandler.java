package io.axoniq.foodordering.query.handler;

import io.axoniq.foodordering.coreapi.ProductCreatedEvent;
import io.axoniq.foodordering.coreapi.data.domain.ProductEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
// command handler and query handler belong to this processing group
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;

    public ProductEventsHandler(ProductsRepository productRepository) {
        this.productsRepository = productRepository;
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
    public void on(ProductCreatedEvent event) throws Exception {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        try {
            productsRepository.save(productEntity);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        if(true) throw new Exception("An exception was thrown in the command event create product ");
    }
}
