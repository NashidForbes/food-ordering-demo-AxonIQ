package io.axoniq.foodordering.coreapi

import java.util.*

data class FoodCartCreatedEvent(
    val foodCartId: UUID,
    val products: Map<UUID, Integer> = HashMap()
)


data class FoodCartAddProductEvent(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)

data class FoodCartRemoveProductEvent(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)


