package org.example.pantrywisecmp.product.domain

data class Product(
    val id: Int = 0,
    val name: String,
    val details: String,
    val quantity: Double? = null,
    val productUnit: ProductUnit,
    val category: ProductCategory = ProductCategory.OTHER,
    val productStatus: ProductStatus = ProductStatus.INVENTORY,
    val expirationDate: Long? = null,
    val selected: Boolean = false
)
