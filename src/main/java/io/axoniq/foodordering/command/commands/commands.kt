package io.axoniq.foodordering.command.commands

import org.axonframework.commandhandling.RoutingKey
import org.axonframework.modelling.command.TargetAggregateIdentifier

class CreateFoodCartCommand(@RoutingKey val foodCartId: String, val products: Map<String, Int>)

data class AddProductToCartCommand(
    @TargetAggregateIdentifier val foodCartId: String,
    val productId: String,
    val quantity: Int,
)

data class RemoveProductFromCartCommand(
    @TargetAggregateIdentifier val foodCartId: String,
    val productId: String,
    val quantity: Int
) {

}


