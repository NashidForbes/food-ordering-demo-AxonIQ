package io.axoniq.foodordering.coreapi

import java.util.*

data class FoodCartCreatedEvent(
    val foodCartId: UUID
)

data class ProductCreatedEvent(
    val productId: UUID,
    val name: String,
    val quantity: Int
)

data class ProductSelectedEvent(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)

data class ProductDeselectedEvent(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)

data class OrderConfirmedEvent(
    val foodCartId: UUID
)

data class ProductStockSelectedEvent(
    val productId: UUID,
    val quantity: Int
)

data class ProductStockRemovedEvent(
    val productId: UUID,
    val quantity: Int
)

data class ProductStockOrderConfirmedEvent(
    val productId: UUID,
    val quantity: Int
)