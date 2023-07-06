package io.axoniq.foodordering.query.handler;

import io.axoniq.foodordering.coreapi.FindFoodCartQuery;
import io.axoniq.foodordering.coreapi.data.domain.FoodCartEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.FoodCartRepository;
import io.axoniq.foodordering.query.model.FoodCartRestModelView;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@ProcessingGroup("foodcart-group")
public class FoodCartQueryHandler {

    private final FoodCartRepository foodCartRepository;

    public FoodCartQueryHandler(FoodCartRepository foodCartRepository) {
        this.foodCartRepository = foodCartRepository;
    }

    @QueryHandler
    public FoodCartRestModelView handle(FindFoodCartQuery query) {
        FoodCartRestModelView foodCartRestModelView = new FoodCartRestModelView();

        Optional<FoodCartEntity> foodCart = foodCartRepository.findById(query.getFoodCartId());
        assert foodCart.isPresent();

    /*    List<ProductEntity> products = foodCart.get().getProducts();
        List<ProductRestModel> productsRestModelList = new ArrayList<>();
        ProductRestModel productRestModel = new ProductRestModel();

        BeanUtils.copyProperties(foodCart.get(), foodCartRestModelView);

        productsRestModelList = products.stream()
                .map(product -> {
                    BeanUtils.copyProperties(product, productRestModel);
                    return productRestModel;
                })
                .collect(Collectors.toList());*/

        //foodCartRestModelView.setProducts(productsRestModelList);

        return foodCartRestModelView;
    }

}
