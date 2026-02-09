package org.example.pantrywisecmp.product.presentation.inventory

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.presentation.components.CategorizedProductGrid
import org.example.pantrywisecmp.product.presentation.components.ProductExpirationDateInfo
import org.example.pantrywisecmp.product.presentation.product_action_menu.ProductMenuAction
import org.example.pantrywisecmp.product.presentation.product_action_menu.ProductMenuDialogRoot
import org.example.pantrywisecmp.product.presentation.product_action_menu.ProductMenuViewModel
import org.example.pantrywisecmp.product.presentation.util.getProductAmountText
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import pantrywisecmp.composeapp.generated.resources.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreenRoot(
    inventoryViewModel: InventoryScreenViewModel = koinViewModel()
) {
    val state by inventoryViewModel.state.collectAsState()
    InventoryScreenContent(
        state = state,
        onAction = inventoryViewModel::onAction
    )
    state.selectedProduct?.let {
        val productMenuViewModel = koinViewModel<ProductMenuViewModel>()

        LaunchedEffect(it) {
            productMenuViewModel.onAction(ProductMenuAction.OnProductSelected(it))
        }

        ProductMenuDialogRoot(
            productMenuViewModel,
            onDismiss = { inventoryViewModel.onAction(InventoryScreenAction.OnProductDeselected) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InventoryScreenContent(
    state: InventoryScreenState,
    onAction: (InventoryScreenAction) -> Unit
) {
    CategorizedProductGrid(
        modifier = Modifier
            .fillMaxSize(),
        categoryToProductListMap = state.categoryToProductListMap,
        expandedCategories = state.expandedCategories,
        onToggleExpanded = { category ->
            onAction(InventoryScreenAction.OnExpandCategoryClick(category))
        },
        stickyHeader = {
            if (state.multiSelectionModeActive)
                MultiSelectionPanel(
                    onCancelSelectionClick = { onAction(InventoryScreenAction.OnCancelSelectionModeClick) },
                    onDeleteClick = { onAction(InventoryScreenAction.OnDeleteProductListClick) },
                )
        },
        productCard = {
            InventoryProductCard(
                product = it,
                onClick = { onAction(InventoryScreenAction.OnProductClick(it)) },
                onLongClick = { onAction(InventoryScreenAction.OnProductLongClick(it)) },
            )
        }
    )
}


@Composable
fun MultiSelectionPanel(
    onCancelSelectionClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val padding = 8.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(Res.string.common_cancel),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = padding)
                .clickable { onCancelSelectionClick() }
        )
        Row(
            modifier = Modifier
                .padding(vertical = padding)
                .clickable { onDeleteClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Delete, contentDescription = null)
            Text(
                text = stringResource(Res.string.common_delete),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun InventoryProductCard(
    product: Product,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val containerColor =
        if (product.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
    val contentColor =
        if (product.selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .combinedClickable(onClick = onClick, onLongClick = onLongClick)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            if (product.details.isNotEmpty())
                Text(
                    text = product.details,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            if (product.quantity != null)
                Text(
                    text = getProductAmountText(product),
                    style = MaterialTheme.typography.bodySmall
                )
            product.expirationDate?.let {
                ProductExpirationDateInfo(
                    expirationTimeStamp = it,
                    isBackgroundColorDefault = !product.selected
                )
            }
        }
    }
}