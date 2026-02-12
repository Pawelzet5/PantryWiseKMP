package org.example.pantrywisecmp.product.presentation.adding_products

import org.example.pantrywisecmp.product.presentation.model.ProductDraft
import org.example.pantrywisecmp.product.domain.ProductSuggestion


data class SearchAndSelectProductsState(
    val queryText: String = "",
    val selectedProducts: List<ProductDraft> = emptyList(),
    val suggestedProducts: List<ProductSuggestion> = emptyList(),
    val recentProducts: List<ProductSuggestion> = emptyList(),
    val selectedTabIndex: Int
)
