package io.axoniq.foodordering.command.interceptors;

import io.axoniq.foodordering.coreapi.CreateProductCommand;
import io.axoniq.foodordering.coreapi.data.domain.ProductLookupEntity;
import io.axoniq.foodordering.coreapi.data.domain.interfaces.ProductLookupRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Slf4j
@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    // This CreateProductCommandInterceptor is used to intercept the CreateProductCommand
    // not just CreateProductCommand, but also UpdateProductCommand, DeleteProductCommand, etc.
    // for now we just want to intercept the CreateProductCommand messages
    @NotNull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@NotNull List<? extends CommandMessage<?>> messages) {
        return (index, commandMessage) -> {

            CreateProductCommand createProductCommand = (CreateProductCommand) commandMessage.getPayload();

            log.info("CreateProductCommandInterceptor intercepted command: {}", commandMessage);

            if(CreateProductCommand.class.equals(commandMessage.getPayloadType())){
                if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Price cannot be less or equal than zero");
                }

                if(createProductCommand.getName() == null
                        || createProductCommand.getName().isBlank()) {
                    throw new IllegalArgumentException("Title cannot be empty");
                }

                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrName(createProductCommand.getProductId(), createProductCommand.getName());
                   if(productLookupEntity != null) {
                       throw new IllegalStateException(String.format("Product with id %s or title %s already exists",
                               createProductCommand.getProductId(), createProductCommand.getName()));
                   }
            }

            return commandMessage;
        };


    }
}