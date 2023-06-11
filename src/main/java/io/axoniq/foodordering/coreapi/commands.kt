package io.axoniq.foodordering.coreapi

import org.axonframework.commandhandling.RoutingKey
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*

class CreateFoodCartCommand(@RoutingKey val foodCartId: UUID)
class CreateProductCommand(@RoutingKey val productId: UUID, val name : String, val price: BigDecimal, val quantity: Int)

data class SelectProductCommand(
    @TargetAggregateIdentifier val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)

data class DeselectProductCommand(
    @TargetAggregateIdentifier val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)

data class ConfirmOrderCommand(
    @TargetAggregateIdentifier val foodCartId: UUID
)

data class SelectProductStockCommand(
    @TargetAggregateIdentifier val productId: UUID,
    val quantity: Int
)

data class RemoveProductStockCommand(
    @TargetAggregateIdentifier val productId: UUID,
    val quantity: Int
)

data class ConfirmProductStockOrderCommand(
    @TargetAggregateIdentifier val productId: UUID
)

