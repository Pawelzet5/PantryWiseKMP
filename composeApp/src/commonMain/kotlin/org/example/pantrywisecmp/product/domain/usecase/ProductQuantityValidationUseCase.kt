package org.example.pantrywisecmp.product.domain.usecase

sealed interface QuantityValidationResult {
    object InvalidNumber : QuantityValidationResult
    object Negative : QuantityValidationResult
    object Success: QuantityValidationResult
}

class ProductQuantityValidationUseCase {
    fun invoke(quantityText: String?): QuantityValidationResult {
        val quantityDouble = quantityText?.toDoubleOrNull()
        return when {
            quantityText.isNullOrBlank() -> QuantityValidationResult.Success
            quantityDouble == null -> QuantityValidationResult.InvalidNumber
            quantityDouble < 0 -> QuantityValidationResult.Negative
            else -> QuantityValidationResult.Success
        }
    }
}