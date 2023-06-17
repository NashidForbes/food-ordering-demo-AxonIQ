package io.axoniq.foodordering.coreapi.handler.errorhandling;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;
import org.jetbrains.annotations.NotNull;

public class ProductsServiceEventErrorHandler implements ListenerInvocationErrorHandler {
    @Override
    public void onError(@NotNull Exception exception, @NotNull EventMessage<?> event, @NotNull EventMessageHandler eventHandler) throws Exception {
        throw exception;
    }
}
