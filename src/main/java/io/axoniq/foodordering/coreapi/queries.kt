package io.axoniq.foodordering.coreapi

import java.util.*

data class FindFoodCartQuery(val foodCartId: String)
data class FindProductQuery(val productId: String)
data class FindProductByNameQuery(val productName: String)
class FindProductsQuery

class RetrieveProductOptionsQuery