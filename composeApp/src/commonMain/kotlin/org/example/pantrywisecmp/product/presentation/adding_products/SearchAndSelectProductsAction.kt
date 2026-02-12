package org.example.pantrywisecmp.product.presentation.adding_products

import org.example.pantrywisecmp.product.presentation.model.ProductDraft
import org.example.pantrywisecmp.product.domain.ProductSuggestion


sealed interface SearchAndSelectProductsAction {
    data class OnQueryTextInput(val newText: String) : SearchAndSelectProductsAction
    data class OnProductSuggestionClick(val productSuggestion: ProductSuggestion) :
        SearchAndSelectProductsAction

    data class OnProductClick(val productDraft: ProductDraft) :
        SearchAndSelectProductsAction

    data class OnDeleteProduct(val productDraft: ProductDraft) :
        SearchAndSelectProductsAction

    data class OnConfirmProductInputClick(val productDraft: ProductDraft) :
        SearchAndSelectProductsAction
    data class OnTabSelected(val index: Int): SearchAndSelectProductsAction
    data object OnAddProductManually : SearchAndSelectProductsAction
    data object OnCancelProductInputClick : SearchAndSelectProductsAction
    data object OnAddProductList : SearchAndSelectProductsAction
}