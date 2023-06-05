package io.axoniq.foodordering.gui;

import io.axoniq.foodordering.coreapi.CreateFoodCartCommand;
import io.axoniq.foodordering.coreapi.FindFoodCartQuery;
import io.axoniq.foodordering.query.FoodCartView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class FoodOrderController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public FoodOrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/create")
    public void handle(){
        // CreateFoodCartCommand() doesn't create anything / contain anythiing
        // It just tells the world we want to create an object
        this.commandGateway.send(new CreateFoodCartCommand(UUID.randomUUID()));
    }

    @GetMapping("/foodcart/{foodCartId}")
    public CompletableFuture<FoodCartView> handle(@PathVariable("foodCartId") String foodCartId){
        return this.queryGateway.query(new FindFoodCartQuery(UUID.fromString(foodCartId)),
                ResponseTypes.instanceOf(FoodCartView.class));
    }
}
