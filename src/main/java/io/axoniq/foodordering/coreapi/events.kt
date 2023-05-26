package io.axoniq.foodordering.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class FoodCartCreatedEvent(
    val foodCartId: UUID
)

data class SelectProductEvent(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)

data class DeselectProductEvent(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)

data class ConfirmOrderEvent(
    val foodCartId: UUID
)