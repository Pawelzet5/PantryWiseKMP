package org.example.pantrywisecmp.product.presentation.product_action_menu

import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductDraft
import org.example.pantrywisecmp.product.domain.ProductUnit

sealed interface ProductMenuAction {
    data object OnRemoveProductClick : ProductMenuAction
    data object OnMoveProductClick : ProductMenuAction
    data object OnFullEditClick : ProductMenuAction
    data object OnConfirmEditClick : ProductMenuAction
    data object OnCancelFullEditClick : ProductMenuAction
    data class OnConfirmFullEditClick(val productDraft: ProductDraft) : ProductMenuAction
    data class OnQuantityInput(val quantity: String) : ProductMenuAction
    data class OnUnitSelected(val unit: ProductUnit) : ProductMenuAction

    data class OnProductSelected(val product: Product): ProductMenuAction
}