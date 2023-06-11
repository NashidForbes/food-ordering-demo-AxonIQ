package io.axoniq.foodordering.coreapi

// This class is for consumer
class ProductDeselectionException(message: String) : Exception(message)

// This class is for backend administrators
class ProductStockDeselectionException(message: String) : Exception(message)
