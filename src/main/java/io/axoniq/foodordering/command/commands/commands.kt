package io.axoniq.foodordering.command.commands

import io.axoniq.foodordering.query.model.ProductRestModel
import org.axonframework.commandhandling.RoutingKey
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

class CreateFoodCartCommand(@RoutingKey val foodCartId: UUID, val products: Map<UUID, Integer>,)

data class AddProductToCartCommand(
    @TargetAggregateIdentifier val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int,
)

data class RemoveProductFromCartCommand(
    @TargetAggregateIdentifier val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
) {

}


