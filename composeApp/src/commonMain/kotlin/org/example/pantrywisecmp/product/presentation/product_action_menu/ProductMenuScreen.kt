package org.example.pantrywisecmp.product.presentation.product_action_menu

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.product.domain.ProductUnit
import org.example.pantrywisecmp.product.presentation.components.ProductQuantityAndUnitInput
import org.example.pantrywisecmp.product.presentation.product_input.*
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import pantrywisecmp.composeapp.generated.resources.*


@Composable
fun ProductMenuDialogRoot(
    viewModel: ProductMenuViewModel,
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    ProductMenuDialog(
        state = state,
        onAction = viewModel::onAction,
        onDismiss = onDismiss
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductMenuDialog(
    state: ProductMenuState,
    onAction: (ProductMenuAction) -> Unit,
    onDismiss: () -> Unit
) {
    val productInputViewModel = koinViewModel<ProductInputViewModel>()
    LaunchedEffect(state.productDraft) {
        if (state.productDraft != null)
            productInputViewModel.onAction(ProductInputAction.OnProductUpdated(state.productDraft))
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val isProductDraftNotNull = state.productDraft != null
        AnimatedContent(targetState = isProductDraftNotNull) { showFullEdit ->
            if (showFullEdit && isProductDraftNotNull)
                ProductInputScreenRoot(
                    modifier = Modifier.fillMaxWidth(),
                    confirmInputTextStringRes = Res.string.common_save,
                    viewModel = productInputViewModel,
                    onSubmitInput = {
                        onAction(ProductMenuAction.OnConfirmFullEditClick(it))
                    },
                    onCancel = onDismiss
                )
            else
                ProductActionMenu(
                    productName = state.selectedProduct?.name?: "No product",
                    productQuantity = state.quantity,
                    productUnit = state.unit,
                    productQuantityErrorText = state.quantityErrorText,
                    onAction = onAction,
                    onDismiss = onDismiss
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductActionMenu(
    productName: String,
    productQuantity: String,
    productUnit: ProductUnit,
    productQuantityErrorText: UiText? = null,
    onAction: (ProductMenuAction) -> Unit,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(
            text = productName,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        ActionMenuItem(
            label = stringResource(Res.string.product_menu_option_move_to_shopping_list),
            icon = Icons.Default.ShoppingCart,
            onClick = {
                onAction(ProductMenuAction.OnMoveProductClick)
                onDismiss()
            }
        )
        ActionMenuItem(
            label = stringResource(Res.string.common_remove),
            icon = Icons.Default.Delete,
            onClick = {
                onAction(ProductMenuAction.OnRemoveProductClick)
                onDismiss()
            }
        )
        ProductQuantityAndUnitInput(
            quantity = productQuantity,
            quantityErrorText = productQuantityErrorText,
            unit = productUnit,
            onQuantityInput = {
                onAction(ProductMenuAction.OnQuantityInput(it))
            },
            onUnitSelected = {
                onAction(ProductMenuAction.OnUnitSelected(it))
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(Res.string.product_menu_option_full_edit),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 4.dp)
                    .clickable {
                        onAction(ProductMenuAction.OnFullEditClick)
                    }

            )
            Text(
                text = stringResource(Res.string.common_save),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 4.dp)
                    .clickable {
                        onAction(ProductMenuAction.OnConfirmEditClick)
                    }

            )
        }
    }
}

@Composable
fun ActionMenuItem(label: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(imageVector = icon, contentDescription = label)
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = label,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}