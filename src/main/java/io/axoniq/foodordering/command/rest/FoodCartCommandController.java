package io.axoniq.foodordering.command.rest;

import io.axoniq.foodordering.command.commands.RemoveProductFromCartCommand;
import io.axoniq.foodordering.command.model.CreateFoodCartRestModel;
import io.axoniq.foodordering.command.commands.AddProductToCartCommand;
import io.axoniq.foodordering.command.commands.CreateFoodCartCommand;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/foodcart")
@RestController
public class FoodCartCommandController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public FoodCartCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/create")
    public void handle(@Valid @RequestBody CreateFoodCartRestModel cart) {
        Map<String, Integer> products = new HashMap();

        commandGateway.send(new CreateFoodCartCommand(UUID.randomUUID().toString(), products));
    }

    @PostMapping("/{foodCartId}/add/{productId}/quantity/{quantity}")
    public void addProductToCart(@PathVariable("foodCartId") String foodCartId,
                                 @PathVariable("productId") String productId,
                                 @PathVariable("quantity") Integer quantity) {
        commandGateway.send(new AddProductToCartCommand(
                foodCartId, productId, quantity
        ));
    }

    @PostMapping("/{foodCartId}/remove/{productId}/quantity/{quantity}")
    public void removeProductFromCartCommand(@PathVariable("foodCartId") String foodCartId,
                                             @PathVariable("productId") String productId,
                                             @PathVariable("quantity") Integer quantity) {
        commandGateway.send(new RemoveProductFromCartCommand(
                foodCartId, productId, quantity
        ));
    }
}
