package io.axoniq.foodordering;

import io.axoniq.foodordering.command.interceptors.CreateProductCommandInterceptor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FoodOrderingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderingApplication.class, args);
    }

    // register CreateProductCommand Message Interceptor
    @Autowired
    public void registerCreateProductCommandMessageInterceptor(ApplicationContext context, CommandBus commandBus) {

      commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
    }
}
