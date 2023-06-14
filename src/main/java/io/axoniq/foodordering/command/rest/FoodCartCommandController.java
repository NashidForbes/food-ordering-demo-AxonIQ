package io.axoniq.foodordering.command.rest;

import io.axoniq.foodordering.command.model.CreateFoodCartCommand;
import io.axoniq.foodordering.command.model.CreateFoodCartRestModel;
import io.axoniq.foodordering.query.model.ProductRestModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        List<ProductRestModel> products = new ArrayList<>();

        commandGateway.send(new CreateFoodCartCommand(UUID.randomUUID(), products));
    }
}
