package io.axoniq.foodordering.command.handler;

import io.axoniq.foodordering.coreapi.ProductCreatedEvent;
import io.axoniq.foodordering.coreapi.data.domain.ProductLookupEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.ProductLookupRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;

@Component
// command handler and query handler belong to this processing group
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    // autowired via constructor
    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(), event.getName());
        // can do a check here if product entity exists already
        // but we'll do this check in message dispatch interceptor class.

        productLookupRepository.save(productLookupEntity);
    }

    // Clear or initialize the READ ONLY productsLookup database tables when doing replays from event store
    @ResetHandler
    public void reset(){
        productLookupRepository.deleteAll();
    }
}
