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
    public String createProduct(@Valid @RequestBody CreateProductRestModel product) {

        String returnValue = "";

        // No need to wrap below in a try-catch block as the exception is handled in the
        // ProductsServiceErrorHandler centralized error handler class
        returnValue = this.commandGateway.sendAndWait(new CreateProductCommand(UUID.randomUUID(), product.getName(), product.getPrice(),
                product.getQuantity()));

/*        try{
         returnValue = this.commandGateway.sendAndWait(new CreateProductCommand(UUID.randomUUID(), product.getName(), product.getPrice(),
                    product.getQuantity()));
        } catch (Exception ex){
           returnValue = ex.getLocalizedMessage();
        }*/

        return returnValue;
    }
}
