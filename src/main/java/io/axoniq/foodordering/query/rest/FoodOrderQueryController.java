package io.axoniq.foodordering.query.rest;

import io.axoniq.foodordering.coreapi.DeselectProductCommand;
import io.axoniq.foodordering.coreapi.FindFoodCartQuery;
import io.axoniq.foodordering.query.model.FoodCartRestModelView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/foodCartQuery")
@RestController
public class FoodOrderQueryController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;


    public FoodOrderQueryController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }


    @GetMapping("/{foodCartId}")
    public CompletableFuture<FoodCartRestModelView> findFoodCart(@PathVariable("foodCartId") String foodCartId) {
        return queryGateway.query(
                new FindFoodCartQuery(UUID.fromString(foodCartId)),
                ResponseTypes.instanceOf(FoodCartRestModelView.class)
        );
    }

    @PostMapping("/{foodCartId}/deselect/{productId}/quantity/{quantity}")
    public void deselectProduct(@PathVariable("foodCartId") String foodCartId,
                                @PathVariable("productId") String productId,
                                @PathVariable("quantity") Integer quantity) {
        commandGateway.send(new DeselectProductCommand(
                UUID.fromString(foodCartId), UUID.fromString(productId), quantity
        ));
    }

/*    @GetMapping("/foodcart/{foodCartId}")
    public CompletableFuture<FoodCartView> handle(@PathVariable("foodCartId") String foodCartId) {
        return this.queryGateway.query(new FindFoodCartQuery(UUID.fromString(foodCartId)),
                ResponseTypes.instanceOf(FoodCartView.class));
    }*/
}
