package org.example.pantrywisecmp.product.presentation.shopping_list

import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductCategory


sealed interface ShoppingListScreenAction {
    data class OnCategoryHeaderClick(val category: ProductCategory) : ShoppingListScreenAction
    data class OnProductCheckedChange(val product: Product, val isChecked: Boolean) :
        ShoppingListScreenAction
}