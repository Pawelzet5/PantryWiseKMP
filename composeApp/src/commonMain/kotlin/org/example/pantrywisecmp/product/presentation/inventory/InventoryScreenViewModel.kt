package org.example.pantrywisecmp.product.presentation.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.example.pantrywisecmp.core.domain.extensions.toggle
import org.example.pantrywisecmp.product.domain.*

class InventoryScreenViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {
    private val TAG = "InventoryScreenViewModel"

    private val productsFlow: Flow<List<Product>> =
        productRepository.getProducts(ProductStatus.INVENTORY)
            .distinctUntilChanged()

    private val multiSelectionModeActive = MutableStateFlow(false)
    private val expandedCategories = MutableStateFlow<Set<ProductCategory>>(emptySet())
    private val selectedProduct = MutableStateFlow<Product?>(null)
    private val selectedIds = MutableStateFlow<Set<Int>>(emptySet())

    val state: StateFlow<InventoryScreenState> =
        combine(
            productsFlow,
            multiSelectionModeActive,
            selectedIds,
            expandedCategories,
            selectedProduct
        ) { products,
            multiSelectActive,
            selectedIdsSet,
            expandedCategories,
            selectedProduct ->

            val selectedProductList: List<Product> = products.map { product ->
                val isSelected = multiSelectActive && product.id in selectedIdsSet
                product.copy(selected = isSelected)
            }

            val categoryMap: Map<ProductCategory, List<Product>> =
                selectedProductList.groupBy { it.category }

            InventoryScreenState(
                categoryToProductListMap = categoryMap,
                selectedProduct = selectedProduct,
                expandedCategories = expandedCategories,
                multiSelectionModeActive = multiSelectActive
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = InventoryScreenState()
        )

    fun onAction(action: InventoryScreenAction) {
        when (action) {
            InventoryScreenAction.OnProductDeselected -> selectedProduct.update { null }

            is InventoryScreenAction.OnExpandCategoryClick ->
                expandedCategories.update { it.toggle(action.productCategory) }

            is InventoryScreenAction.OnProductClick ->
                handleProductClick(action.product)

            is InventoryScreenAction.OnProductLongClick ->
                handleProductLongClick(action.product)

            InventoryScreenAction.OnCancelSelectionModeClick ->
                handleCancelSelectionMode()

            InventoryScreenAction.OnDeleteProductListClick ->
                handleDeleteProductListClick()
        }
    }

    private fun handleProductClick(product: Product) {
        if (multiSelectionModeActive.value) {
            toggleProductSelected(product)
            if (selectedIds.value.isEmpty()) {
                multiSelectionModeActive.update { false }
            }
        } else {
            selectedProduct.update { product }
        }
    }

    private fun handleProductLongClick(product: Product) {
        multiSelectionModeActive.update { true }
        toggleProductSelected(product)
    }

    private fun toggleProductSelected(product: Product) {
        selectedIds.update { current ->
            current.toggle(product.id)
        }
    }

    private fun handleCancelSelectionMode() {
        multiSelectionModeActive.update { false }
        selectedIds.update { emptySet() }
    }

    private fun handleDeleteProductListClick() = viewModelScope.launch(Dispatchers.IO) {
        val idsToDelete = selectedIds.value
        if (idsToDelete.isEmpty()) return@launch


        productRepository.deleteProductList(idsToDelete)

        multiSelectionModeActive.update { false }
        selectedIds.update { emptySet() }
    }
}