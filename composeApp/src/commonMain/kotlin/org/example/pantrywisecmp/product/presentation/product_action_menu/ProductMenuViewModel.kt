package org.example.pantrywisecmp.product.presentation.product_action_menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.core.domain.UiText.StringResourceId
import org.example.pantrywisecmp.core.utils.LogUtils
import org.example.pantrywisecmp.product.domain.*
import org.example.pantrywisecmp.product.domain.usecase.ProductQuantityValidationUseCase
import org.example.pantrywisecmp.product.domain.usecase.QuantityValidationResult
import pantrywisecmp.composeapp.generated.resources.*
import kotlin.uuid.ExperimentalUuidApi

class ProductMenuViewModel(
    private val productRepository: ProductRepository,
    private val quantityValidationUseCase: ProductQuantityValidationUseCase
) : ViewModel() {
    private val TAG = "ProductMenuViewModel"

    private val _state = MutableStateFlow(ProductMenuState())
    val state: StateFlow<ProductMenuState> = _state.asStateFlow()

    fun onAction(action: ProductMenuAction) {
        when (action) {
            ProductMenuAction.OnConfirmEditClick -> handleConfirmEdit()
            ProductMenuAction.OnFullEditClick -> handleFullEditClick()
            ProductMenuAction.OnMoveProductClick -> moveProductToShoppingList()
            ProductMenuAction.OnRemoveProductClick -> deleteProduct()
            is ProductMenuAction.OnConfirmFullEditClick -> handleFullEditClick()
            is ProductMenuAction.OnQuantityInput -> _state.update { it.copy(quantity = action.quantity) }
            is ProductMenuAction.OnUnitSelected -> _state.update { it.copy(unit = action.unit) }
            is ProductMenuAction.OnProductSelected -> _state.update { ProductMenuState(product = action.product) }
        }
    }

    private fun handleFullEditClick() {
        _state.update { it.copy(productDraft = getProductDraft()) }
    }

    private fun handleConfirmEdit() {
        val quantityErrorText = validateQuantity(state.value.quantity)

        if (quantityErrorText == null) {
            state.value.let { state ->
                state.selectedProduct?.copy(
                    quantity = state.quantity.toDoubleOrNull(),
                    productUnit = state.unit
                )?.let { newProduct ->
                    updateProduct(newProduct)
                }
            }
        } else {
            _state.update {
                it.copy(quantityErrorText = quantityErrorText)
            }
        }
    }

    private fun updateProduct(newProduct: Product) = viewModelScope.launch(Dispatchers.IO) {
        try {
            productRepository.updateProduct(newProduct)
        } catch (e: Exception) {
            LogUtils.error("Problem occurred while updating product", TAG, e)
        }
    }


    private fun deleteProduct() = viewModelScope.launch(Dispatchers.IO) {
        try {
            state.value.selectedProduct?.let { product ->
                productRepository.deleteProduct(product)
            }
        } catch (e: Exception) {
            LogUtils.error("Problem occurred while deleting product", TAG, e)
        }
    }

    private fun moveProductToShoppingList() = viewModelScope.launch(Dispatchers.IO) {
        try {
            state.value.selectedProduct?.let { product ->
                productRepository.updateProduct(
                    product.copy(productStatus = ProductStatus.SHOPPING_LIST)
                )
            }
        } catch (e: Exception) {
            LogUtils.error("Problem occurred while moving product to shopping list", TAG, e)
        }
    }


    private fun validateQuantity(quantity: String): UiText? {
        val validationResult = quantityValidationUseCase.invoke(quantity)
        return when (validationResult) {
            QuantityValidationResult.InvalidNumber -> StringResourceId(Res.string.product_input_invalid_quantity_validation_message)
            QuantityValidationResult.Negative -> StringResourceId(Res.string.product_input_negative_quantity_validation_message)
            QuantityValidationResult.Success -> null
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun getProductDraft() = state.value.selectedProduct?.run {
        ProductDraft(
            name = name,
            details = details,
            quantity = quantity,
            productUnit = productUnit,
            category = category,
            expirationDate = expirationDate
        )
    }
}