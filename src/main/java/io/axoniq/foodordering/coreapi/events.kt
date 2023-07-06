package io.axoniq.foodordering.coreapi

import java.util.*

data class FoodCartCreatedEvent(
    val foodCartId: String,
    val products: Map<String, Int> = HashMap()
)


data class FoodCartAddProductEvent(
    val foodCartId: String,
    val productId: String,
    val quantity: Int
)

data class FoodCartRemoveProductEvent(
    val foodCartId: String,
    val productId: String,
    val quantity: Int
)


