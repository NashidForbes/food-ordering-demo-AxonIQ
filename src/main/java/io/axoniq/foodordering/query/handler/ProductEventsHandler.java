package io.axoniq.foodordering.query.handler;

import io.axoniq.foodordering.coreapi.ProductCreatedEvent;
import io.axoniq.foodordering.coreapi.data.domain.ProductEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.ProductsRepository;
import io.axoniq.foodordering.query.model.FindProductsQuery;
import io.axoniq.foodordering.query.model.ProductRestModel;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
// command handler and query handler belong to this processing group
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;

    public ProductEventsHandler(ProductsRepository productRepository) {
        this.productsRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery query){

        List<ProductRestModel> productsRest = new ArrayList<>();

        List<ProductEntity> storedProducts = productsRepository.findAll();

        for(ProductEntity productEntity: storedProducts){
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productsRest.add(productRestModel);
        }

        return productsRest;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        try {
            productsRepository.save(productEntity);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
