@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package org.example.pantrywisecmp.product.presentation.adding_products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import org.example.pantrywisecmp.core.domain.extensions.updateFirst
import org.example.pantrywisecmp.core.presentation.model.NavigationEvent
import org.example.pantrywisecmp.product.domain.ProductStatus
import org.example.pantrywisecmp.product.domain.ProductSuggestion
import org.example.pantrywisecmp.product.domain.repository.ProductRepository
import org.example.pantrywisecmp.product.domain.repository.SuggestionsRepository
import org.example.pantrywisecmp.product.presentation.model.ProductDraft
import org.example.pantrywisecmp.product.presentation.model.toProduct
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class SearchAndSelectProductsViewModel(
    private val suggestionsRepository: SuggestionsRepository,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val suggestionResults = searchQuery
        .debounce(300L)
        .flatMapLatest { query ->
            flowOf(
                suggestionsRepository.getSuggestions(query)
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )
    private val recentProductsList = suggestionsRepository.getRecentProductsSuggestions()
    private val selectedProducts = MutableStateFlow<List<ProductDraft>>(emptyList())
    private val selectedTabIndex = MutableStateFlow(ProductAddingTab.SELECTED.ordinal)

    private val _selectedProduct = MutableStateFlow<ProductDraft?>(null)
    val selectedProduct = _selectedProduct.asStateFlow()

    private val navigationChannel = Channel<NavigationEvent>()
    val navigationEventsChannelFlow = navigationChannel.receiveAsFlow()

    var viewState = combine(
        searchQuery,
        suggestionResults,
        selectedProducts,
        recentProductsList,
        selectedTabIndex
    ) { query, suggestions, selectedProducts, recentProducts, selectedTabIndex ->
        val selectedProductNames = selectedProducts.map { it.name }
        val productSuggestions = suggestions.map {
            if (selectedProductNames.contains(it.name))
                it.copy(isAdded = true)
            else it
        }
        SearchAndSelectProductsState(
            queryText = query,
            selectedProducts = selectedProducts,
            suggestedProducts = productSuggestions,
            recentProducts = recentProducts,
            selectedTabIndex = selectedTabIndex
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        SearchAndSelectProductsState(selectedTabIndex = selectedTabIndex.value)
    )

    fun onAction(action: SearchAndSelectProductsAction) {
        when (action) {
            is SearchAndSelectProductsAction.OnProductClick -> _selectedProduct.update { action.productDraft.copy() }
            is SearchAndSelectProductsAction.OnProductSuggestionClick -> handleProductSuggestionClick(
                action.productSuggestion
            )

            is SearchAndSelectProductsAction.OnQueryTextInput -> searchQuery.update { action.newText }
            SearchAndSelectProductsAction.OnCancelProductInputClick -> _selectedProduct.update { null }

            is SearchAndSelectProductsAction.OnDeleteProduct -> selectedProducts.update {
                it.minus(action.productDraft)
            }

            is SearchAndSelectProductsAction.OnTabSelected -> selectedTabIndex.update {
                action.index
            }
            SearchAndSelectProductsAction.OnAddProductList -> handleAddProducts()
            SearchAndSelectProductsAction.OnAddProductManually -> handleAddProductManually()
            is SearchAndSelectProductsAction.OnConfirmProductInputClick -> handleConfirmProductInput(
                action.productDraft
            )
        }
    }

    private fun handleAddProductManually() {
        val newProductName = searchQuery.value
        val newProductInput = ProductDraft(name = newProductName)
        _selectedProduct.update { newProductInput }
    }

    private fun handleConfirmProductInput(productDraft: ProductDraft) {
        selectedProducts.update { productList ->
            if (productList.none { it.tempId == productDraft.tempId })
                productList.plus(productDraft)
            else
                productList.updateFirst(
                    predicate = { it.tempId == productDraft.tempId },
                    transform = { productDraft }
                )
        }
        _selectedProduct.update { null }
        selectedTabIndex.update { ProductAddingTab.SELECTED.ordinal }
    }

    private fun handleProductSuggestionClick(productSuggestion: ProductSuggestion) {
        val selectedProduct =
            selectedProducts.value.find { it.name == productSuggestion.name }
        _selectedProduct.update {
            selectedProduct ?: ProductDraft(name = productSuggestion.name)
        }
    }

    private fun handleAddProducts() = viewModelScope.launch(Dispatchers.IO) {
            selectedProducts.value.map {
                it.toProduct(productStatus = ProductStatus.INVENTORY)
            }.let { productRepository.addProductList(it) }
            navigationChannel.trySend(NavigationEvent.NavigateBack)
    }
}