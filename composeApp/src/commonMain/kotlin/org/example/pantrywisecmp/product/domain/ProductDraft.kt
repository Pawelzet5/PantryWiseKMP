package org.example.pantrywisecmp.product.domain

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ProductDraft(
    val tempId: Uuid = Uuid.random(),
    val name: String,
    val details: String,
    val quantity: Double? = null,
    val productUnit: ProductUnit,
    val category: ProductCategory = ProductCategory.OTHER,
    val expirationDate: Long? = null,
)
