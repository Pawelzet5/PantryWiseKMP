package org.example.pantrywisecmp.product.presentation.shopping_list

import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductCategory

data class ShoppingListScreenState(
    val categoryToProductListMap: Map<ProductCategory, List<Product>> = emptyMap(),
    val expandedCategories: Set<ProductCategory> = emptySet(),
    val completedProducts: List<Product> = emptyList(),
    val isCompletedSectionExpanded: Boolean = false
)