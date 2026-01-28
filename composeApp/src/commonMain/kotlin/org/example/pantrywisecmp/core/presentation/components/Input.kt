package org.example.pantrywisecmp.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.pantrywisecmp.core.domain.UiText
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit?,
    placeholderRes: StringResource,
    labelRes: StringResource,
    errorText: UiText? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyBoardActions: KeyboardActions = KeyboardActions.Default,
) {
    ContentWithLabel(
        modifier = modifier.fillMaxWidth(),
        labelRes = labelRes
    ) {
        StyledTextField(
            modifier = Modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            placeholderRes = placeholderRes,
            errorText = errorText,
            keyboardOptions = keyboardOptions,
            keyBoardActions = keyBoardActions
        )
    }
}

@Composable
fun ClickableReadOnlyStyledTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholderRes: StringResource? = null,
    errorText: UiText? = null,
) {
    StyledTextField(
        modifier = modifier,
        value = value,
        placeholderRes = placeholderRes,
        errorText = errorText,
        enabled = false
    )
}


@Composable
fun StyledTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: ((String) -> Unit)? = null,
    placeholderRes: StringResource? = null,
    errorText: UiText? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyBoardActions: KeyboardActions = KeyboardActions.Default,
    enabled: Boolean = true
) {
    TextField(
        value = value,
        onValueChange = { onValueChange?.invoke(it) },
        readOnly = onValueChange == null,
        enabled = enabled,
        textStyle = MaterialTheme.typography.bodyMedium,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            errorTextColor = MaterialTheme.colorScheme.onErrorContainer,
            errorPlaceholderColor = MaterialTheme.colorScheme.onErrorContainer,
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            errorContainerColor = MaterialTheme.colorScheme.errorContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            placeholderRes?.let {
                Text(
                    text = stringResource(it),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        supportingText = errorText?.let { it ->
            { Text(text = it.asString(), color = MaterialTheme.colorScheme.error) }
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyBoardActions,
        isError = errorText != null,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> ExposedDropdownField(
    modifier: Modifier = Modifier,
    labelRes: StringResource,
    selected: T,
    selectedLabel: @Composable (T) -> String,
    options: List<T>,
    optionLabel: @Composable (T) -> String,
    onSelected: (T) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ContentWithLabel(
        modifier = modifier,
        labelRes = labelRes
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            StyledTextField(
                value = selectedLabel(selected),
                onValueChange = null,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.heightIn(max = 280.dp)
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(optionLabel(item)) },
                        onClick = {
                            onSelected(item)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}