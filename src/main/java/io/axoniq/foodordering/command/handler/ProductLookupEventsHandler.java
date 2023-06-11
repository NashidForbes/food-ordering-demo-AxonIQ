package io.axoniq.foodordering.command.handler;

import org.axonframework.config.ProcessingGroup;
import org.springframework.stereotype.Component;

@Component
// command handler and query handler belong to this processing group
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

}
