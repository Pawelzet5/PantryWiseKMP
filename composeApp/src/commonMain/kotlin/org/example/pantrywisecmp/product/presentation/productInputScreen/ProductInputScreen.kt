package org.example.pantrywisecmp.product.presentation.productInputScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.example.pantrywisecmp.core.presentation.components.*
import org.example.pantrywisecmp.product.domain.ProductCategory
import org.example.pantrywisecmp.product.domain.ProductDraft
import org.example.pantrywisecmp.product.presentation.components.ProductQuantityAndUnitInput
import org.example.pantrywisecmp.product.presentation.helper.DateTimeHelper
import org.example.pantrywisecmp.product.presentation.util.getLabel
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import pantrywisecmp.composeapp.generated.resources.*

@Composable
fun ProductInputScreenRoot(
    modifier: Modifier = Modifier,
    confirmInputTextStringRes: StringResource,
    viewModel: ProductInputViewModel,
    onCancel: () -> Unit,
    onSubmitInput: (ProductDraft) -> Unit
) {
    val productInputState by viewModel.state.collectAsState()

    ProductInputScreen(
        modifier = modifier,
        confirmInputTextStringRes = confirmInputTextStringRes,
        productInputState = productInputState,
        onAction = viewModel::onAction,
        onCancelClick = onCancel,
    )

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is ProductInputEffect.ValidationSuccess -> onSubmitInput(effect.draft)
            }
        }
    }
}

@Composable
fun ProductInputScreen(
    modifier: Modifier,
    confirmInputTextStringRes: StringResource,
    productInputState: ProductInputState,
    onAction: (ProductInputAction) -> Unit,
    onCancelClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier.imePadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(24.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 16.dp)
            ) {
                Text(
                    text = stringResource(Res.string.common_cancel),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding().clickable { onCancelClick() }
                )
                Text(
                    text = stringResource(confirmInputTextStringRes),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable {
                        onAction(ProductInputAction.OnSubmitProductInputClick)
                        onCancelClick()
                    }
                )
            }
        }
        item {
            TextInputField(
                value = productInputState.name,
                onValueChange = {
                    onAction(ProductInputAction.OnNameInput(it))
                },
                placeholderRes = Res.string.product_input_view_name_placeholder,
                labelRes = Res.string.product_input_view_name_label,
                errorText = productInputState.nameErrorText,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
            )
        }
        item {
            TextInputField(
                value = productInputState.details,
                onValueChange = {
                    onAction(ProductInputAction.OnDetailsInput(it))
                },
                placeholderRes = Res.string.product_input_view_details_placeholder,
                labelRes = Res.string.product_input_view_details_label,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
            )
        }
        item {
            ProductCategoryDropdown(
                selected = productInputState.category,
                onSelected = {
                    onAction(ProductInputAction.OnCategorySelected(it))
                },
            )
        }
        item {
            ProductExpirationDateField(
                productInputState.expirationDate,
                onValueChange = {
                    onAction(ProductInputAction.OnDateInput(it))
                },
            )
        }
        item {
            ProductQuantityAndUnitInput(
                quantity = productInputState.quantity,
                quantityErrorText = productInputState.quantityErrorText,
                unit = productInputState.unit,
                onQuantityInput = {
                    onAction(ProductInputAction.OnQuantityInput(it))
                },
                onUnitSelected = {
                    onAction(ProductInputAction.OnUnitSelected(it))
                }
            )
        }
    }
}

@Composable
fun ProductCategoryDropdown(
    selected: ProductCategory,
    modifier: Modifier = Modifier,
    onSelected: (ProductCategory) -> Unit
) {
    ExposedDropdownField(
        modifier = modifier,
        labelRes = Res.string.product_input_view_category_label,
        selected = selected,
        selectedLabel = { it.getLabel().asString() },
        options = ProductCategory.entries.toList(),
        optionLabel = { it.getLabel().asString() },
        onSelected = onSelected
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductExpirationDateField(
    timestamp: Long?,
    modifier: Modifier = Modifier,
    onValueChange: (Long?) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = timestamp ?: DateTimeHelper.getCurrentTimeStamMillis()
    )
    val formattedDate = timestamp?.let { DateTimeHelper.formatTimestampToDayMonthYear(it) } ?: ""
    ContentWithLabel(
        modifier,
        Res.string.product_input_view_expiration_date_label
    ) {
        ClickableReadOnlyStyledTextField(
            modifier = Modifier.fillMaxWidth().clickable { showDatePicker = true },
            value = formattedDate,
            placeholderRes = Res.string.product_input_view_expiration_date_placeholder,
        )
    }
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { selectedTimestamp ->
                            onValueChange(selectedTimestamp)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text(stringResource(Res.string.common_ok))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text(stringResource(Res.string.common_cancel))
                }
            }
        ) {
            DatePicker(
                modifier = Modifier.padding(16.dp),
                state = datePickerState,
                title = { Text(stringResource(Res.string.product_input_view_date_picker_title)) },
                headline = { Text(stringResource(Res.string.product_input_view_date_picker_description)) },
                showModeToggle = false
            )
        }
    }
}