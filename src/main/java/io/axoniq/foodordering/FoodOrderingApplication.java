package io.axoniq.foodordering;

import io.axoniq.core.config.AxonConfig;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import({ AxonConfig.class })
public class FoodOrderingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderingApplication.class, args);
    }

    // register CreateProductCommand Message Interceptor
/*    @Autowired
    public void registerCreateProductCommandMessageInterceptor(ApplicationContext context, CommandBus commandBus) {

        commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
    }*/

    // register the listerInvocationErrorHandler for Product Service Events
/*    @Autowired
    public void configure(EventProcessingConfigurer config) {
        config.registerListenerInvocationErrorHandler("product-group", conf -> new ProductsServiceEventErrorHandler());

        // use Axon's framework PropagatingErrorHandler to propagate general errors to the application below
        //config.registerListenerInvocationErrorHandler("product-group", conf -> PropagatingErrorHandler.instance());
    }*/
}
