package org.example.pantrywisecmp.product.presentation.product_input

import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.product.domain.*
import org.example.pantrywisecmp.product.presentation.model.ProductDraft
import kotlin.uuid.ExperimentalUuidApi

data class ProductInputState(
    val name: String = "",
    val nameErrorText: UiText? = null,
    val details: String = "",
    val quantity: String = "",
    val quantityErrorText: UiText? = null,
    val expirationDate: Long? = null,
    val unit: ProductUnit = ProductUnit.PIECE,
    val category: ProductCategory = ProductCategory.OTHER,
) {
    @OptIn(ExperimentalUuidApi::class)
    fun toDraft(): ProductDraft = ProductDraft(
        name = name,
        details = details,
        quantity = quantity.toDouble(),
        productUnit = unit,
        category = category,
        expirationDate = expirationDate
    )

    companion object {
        fun fromProductDraft(productDraft: ProductDraft) = ProductInputState(
            name = productDraft.name,
            details = productDraft.details,
            quantity = productDraft.quantity.toString(),
            expirationDate = productDraft.expirationDate,
            unit = productDraft.productUnit,
            category = productDraft.category
        )
    }
}
