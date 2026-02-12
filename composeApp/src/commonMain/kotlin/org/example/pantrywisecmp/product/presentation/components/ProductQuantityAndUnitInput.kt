package org.example.pantrywisecmp.product.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.core.presentation.components.ExposedDropdownField
import org.example.pantrywisecmp.core.presentation.components.TextInputField
import org.example.pantrywisecmp.product.domain.ProductUnit
import org.example.pantrywisecmp.product.presentation.util.getLabel
import pantrywisecmp.composeapp.generated.resources.Res
import pantrywisecmp.composeapp.generated.resources.product_input_view_quantity_label
import pantrywisecmp.composeapp.generated.resources.product_input_view_quantity_placeholder
import pantrywisecmp.composeapp.generated.resources.product_input_view_unit_label


@Composable
fun ProductQuantityAndUnitInput(
    quantity: String,
    quantityErrorText: UiText?,
    unit: ProductUnit,
    onQuantityInput: (String) -> Unit,
    onUnitSelected: (ProductUnit) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextInputField(
            value = quantity,
            onValueChange = onQuantityInput,
            placeholderRes = Res.string.product_input_view_quantity_placeholder,
            labelRes = Res.string.product_input_view_quantity_label,
            errorText = quantityErrorText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
            keyBoardActions = keyboardActions,
            modifier = Modifier.weight(1f)
        )

        ProductUnitDropdown(
            selected = unit,
            modifier = Modifier.weight(1f),
            onSelected = onUnitSelected
        )
    }
}

@Composable
fun ProductUnitDropdown(
    selected: ProductUnit,
    modifier: Modifier = Modifier,
    onSelected: (ProductUnit) -> Unit
) {
    ExposedDropdownField(
        modifier = modifier,
        labelRes = Res.string.product_input_view_unit_label,
        selected = selected,
        selectedLabel = { it.getLabel().asStringComposable() },
        options = ProductUnit.entries.toList(),
        optionLabel = { it.getLabel().asStringComposable() },
        onSelected = onSelected
    )
}
