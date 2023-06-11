package io.axoniq.foodordering.coreapi

import java.util.*

data class FindFoodCartQuery(val foodCartId: UUID)
data class FindProductQuery(val productId: UUID)

class RetrieveProductOptionsQuery