package io.axoniq.foodordering.command.rest;

import io.axoniq.foodordering.command.model.CreateProductRestModel;
import io.axoniq.foodordering.coreapi.CreateProductCommand;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/product")
@RestController
public class ProductCommandController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    public ProductCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/create")
    public void handle(@Valid @RequestBody CreateProductRestModel product) {
        this.commandGateway.send(new CreateProductCommand(UUID.randomUUID(), product.getName(), product.getPrice(),
                product.getQuantity()));
    }
}
