package io.axoniq.foodordering.coreapi

import io.axoniq.foodordering.query.model.ProductRestModel
import java.math.BigDecimal
import java.util.*

data class FoodCartCreatedEvent(
    val foodCartId: UUID,
    val products: List<ProductRestModel> = emptyList()
)

data class ProductCreatedEvent(
    val productId: UUID,
    val name: String,
    val price: BigDecimal,
    val quantity: Int
)

data class ProductSelectedEvent(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)

data class ProductStockSelectedEvent(
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


data class ProductStockRemovedEvent(
    val productId: UUID,
    val quantity: Int
)

data class ProductStockOrderConfirmedEvent(
    val productId: UUID,
    val quantity: Int
)