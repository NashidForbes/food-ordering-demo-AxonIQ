package io.axoniq.foodordering.coreapi

import io.axoniq.foodordering.query.model.ProductRestModel
import org.axonframework.commandhandling.RoutingKey
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*

class CreateFoodCartCommand(@RoutingKey val foodCartId: UUID, val orderId: UUID,)

data class AddOrderToCartCommand(
    @TargetAggregateIdentifier val foodCartId: UUID,
    val orderId: UUID,
)

data class RemoveOrderFromCartCommand(
    @TargetAggregateIdentifier val foodCartId: UUID,
    val orderId: UUID,
)


