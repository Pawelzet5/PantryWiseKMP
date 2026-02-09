package org.example.pantrywisecmp.product.presentation.shopping_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import org.example.pantrywisecmp.core.domain.extensions.toggle
import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductCategory
import org.example.pantrywisecmp.product.domain.ProductStatus
import org.example.pantrywisecmp.product.domain.repository.ProductRepository


class ShoppingListViewModel(
    productRepository: ProductRepository
) : ViewModel() {

    private val productsFlow: Flow<List<Product>> =
        productRepository.getProducts(ProductStatus.SHOPPING_LIST)
            .distinctUntilChanged()

    private val expandedCategories = MutableStateFlow<Set<ProductCategory>>(emptySet())
    private val selectedIds = MutableStateFlow<Set<Int>>(emptySet())

    val state: StateFlow<ShoppingListScreenState> = combine(
        productsFlow,
        selectedIds,
        expandedCategories
    ) { productList,
        selectedIdsSet,
        expandedCategories ->

        val products: List<Product> = productList.map { product ->
            val isSelected = product.id in selectedIdsSet
            product.copy(selected = isSelected)
        }

        val categoryMap: Map<ProductCategory, List<Product>> =
            products.filter { it.id !in selectedIdsSet }.groupBy { it.category }

        ShoppingListScreenState(
            categoryToProductListMap = categoryMap,
            expandedCategories = expandedCategories,
            completedProducts = products.filter { it.id in selectedIdsSet }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ShoppingListScreenState()
    )

    fun onAction(action: ShoppingListScreenAction) {
        when (action) {
            is ShoppingListScreenAction.OnCategoryHeaderClick -> expandedCategories.update {
                it.toggle(
                    action.category
                )
            }

            is ShoppingListScreenAction.OnProductCheckedChange -> toggleProductSelected(action.product)
        }
    }

    private fun toggleProductSelected(product: Product) {
        selectedIds.update { current ->
            current.toggle(product.id)
        }
    }
}