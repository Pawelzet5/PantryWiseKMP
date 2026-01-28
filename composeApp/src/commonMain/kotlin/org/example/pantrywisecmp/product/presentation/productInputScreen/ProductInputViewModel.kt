package org.example.pantrywisecmp.product.presentation.productInputScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.core.domain.UiText.StringResourceId
import org.example.pantrywisecmp.product.domain.ProductDraft
import org.example.pantrywisecmp.product.domain.usecase.ProductQuantityValidationUseCase
import org.example.pantrywisecmp.product.domain.usecase.QuantityValidationResult
import pantrywisecmp.composeapp.generated.resources.*
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
class ProductInputViewModel(
    private val quantityValidationUseCase: ProductQuantityValidationUseCase
) : ViewModel() {

    private val _effects = MutableSharedFlow<ProductInputEffect>(extraBufferCapacity = 1)
    val effects = _effects.asSharedFlow()

    private val _state = MutableStateFlow(ProductInputState())
    val state = _state.asStateFlow()

    fun onAction(action: ProductInputAction) {
        when (action) {
            is ProductInputAction.OnCategorySelected -> _state.update {
                it.copy(category = action.value)
            }

            is ProductInputAction.OnDateInput -> _state.update {
                it.copy(expirationDate = action.value)
            }

            is ProductInputAction.OnDetailsInput -> _state.update {
                it.copy(details = action.value)
            }

            is ProductInputAction.OnNameInput -> _state.update {
                it.copy(name = action.value, quantityErrorText = null)
            }

            is ProductInputAction.OnQuantityInput -> _state.update {
                it.copy(quantity = action.value, quantityErrorText = null)
            }

            is ProductInputAction.OnUnitSelected -> _state.update {
                it.copy(unit = action.value)
            }

            ProductInputAction.OnSubmitProductInputClick -> handleSubmitProductInputClick()
            is ProductInputAction.OnProductUpdated -> handleProductUpdated(action.productDraft)
        }
    }

    private fun handleProductUpdated(productDraft: ProductDraft?) {
        _state.update {
            if (productDraft != null)
                ProductInputState.fromProductDraft(productDraft)
            else
                ProductInputState()
        }
    }

    private fun handleSubmitProductInputClick() {
        if (validate())
            viewModelScope.launch {
                _effects.emit(ProductInputEffect.ValidationSuccess(state.value.toDraft()))
            }
    }

    private fun validate(): Boolean {
        val productInputState = state.value
        val nameErrorText =
            if (productInputState.name.isEmpty())
                StringResourceId(Res.string.product_input_empty_name_validation_message)
            else null
        val quantityErrorText = validateQuantity(productInputState.quantity)

        if (nameErrorText == null && quantityErrorText == null) {
            return true
        } else {
            _state.update {
                it.copy(
                    nameErrorText = nameErrorText,
                    quantityErrorText = quantityErrorText
                )
            }
            return false
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
}