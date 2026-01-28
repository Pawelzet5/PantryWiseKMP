package org.example.pantrywisecmp.product.presentation.product_input

import org.example.pantrywisecmp.product.domain.*
import org.example.pantrywisecmp.product.presentation.model.ProductDraft

sealed interface ProductInputAction {
    data class OnNameInput(val value: String) : ProductInputAction
    data class OnDetailsInput(val value: String) : ProductInputAction
    data class OnCategorySelected(val value: ProductCategory) : ProductInputAction
    data class OnDateInput(val value: Long?) : ProductInputAction
    data class OnQuantityInput(val value: String) : ProductInputAction
    data class OnUnitSelected(val value: ProductUnit) : ProductInputAction
    data object OnSubmitProductInputClick : ProductInputAction
    data class OnProductUpdated(val productDraft: ProductDraft?) : ProductInputAction
}