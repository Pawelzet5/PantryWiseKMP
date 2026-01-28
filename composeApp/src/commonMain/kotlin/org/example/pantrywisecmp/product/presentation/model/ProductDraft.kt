package org.example.pantrywisecmp.product.presentation.model

import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductCategory
import org.example.pantrywisecmp.product.domain.ProductStatus
import org.example.pantrywisecmp.product.domain.ProductUnit
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ProductDraft(
    val tempId: Uuid = Uuid.random(),
    val name: String,
    val details: String = "",
    val quantity: Double? = null,
    val productUnit: ProductUnit = ProductUnit.PIECE,
    val category: ProductCategory = ProductCategory.OTHER,
    val expirationDate: Long? = null,
    val isDeleteOptionRevealed: Boolean = false
)

fun ProductDraft.toProduct(productStatus: ProductStatus) = Product(
    name = name,
    details = details,
    quantity = quantity,
    productUnit = productUnit,
    category = category,
    productStatus = productStatus,
    expirationDate = expirationDate
)
