package org.example.pantrywisecmp.product.presentation.inventory

import org.example.pantrywisecmp.product.domain.*

sealed interface InventoryScreenAction {
    data class OnExpandCategoryClick(val productCategory: ProductCategory) : InventoryScreenAction
    data class OnProductClick(val product: Product) : InventoryScreenAction
    data class OnProductLongClick(val product: Product) : InventoryScreenAction

    data object OnCancelSelectionModeClick : InventoryScreenAction
    data object OnDeleteProductListClick : InventoryScreenAction
    data object OnProductDeselected : InventoryScreenAction
}