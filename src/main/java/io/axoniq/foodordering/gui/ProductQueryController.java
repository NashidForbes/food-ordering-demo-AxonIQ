package io.axoniq.foodordering.gui;

import io.axoniq.foodordering.coreapi.FindProductQuery;
import io.axoniq.foodordering.query.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/product")
@RestController
public class ProductQueryController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public ProductQueryController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @GetMapping("/select/{productId}")
    public CompletableFuture<List<ProductRestModel>> selectProduct(@PathVariable("productId") UUID productId) {
        return this.queryGateway.query(new FindProductQuery(productId), ResponseTypes.multipleInstancesOf(ProductRestModel.class));
    }
}
