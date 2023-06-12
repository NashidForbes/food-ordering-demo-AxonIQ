package io.axoniq.foodordering.query.handler;

import io.axoniq.foodordering.coreapi.data.domain.ProductEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.ProductsRepository;
import io.axoniq.foodordering.query.model.FindProductsQuery;
import io.axoniq.foodordering.query.model.ProductRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductQueryHandler {

    private final ProductsRepository productsRepository;

    public ProductQueryHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
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
}
