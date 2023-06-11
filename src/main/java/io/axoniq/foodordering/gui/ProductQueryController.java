package io.axoniq.foodordering.gui;

import io.axoniq.foodordering.coreapi.FindProductQuery;
import io.axoniq.foodordering.query.ProductView;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CompletableFuture<ProductView> selectProduct(@PathVariable("productId") UUID productId) {
        return this.queryGateway.query(new FindProductQuery(productId), ProductView.class);
    }
}
