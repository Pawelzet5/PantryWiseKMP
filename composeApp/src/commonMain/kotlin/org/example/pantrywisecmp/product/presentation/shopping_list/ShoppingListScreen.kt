package org.example.pantrywisecmp.product.presentation.shopping_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.presentation.components.CategorizedProductGrid
import org.example.pantrywisecmp.product.presentation.components.CategoryCard
import org.example.pantrywisecmp.product.presentation.util.getProductAmountText
import org.koin.compose.viewmodel.koinViewModel
import pantrywisecmp.composeapp.generated.resources.Res
import pantrywisecmp.composeapp.generated.resources.shopping_list_completed_card_label

@Composable
fun ShoppingListScreenRoot(viewModel: ShoppingListViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ShoppingListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ShoppingListScreen(
    state: ShoppingListScreenState,
    onAction: (ShoppingListScreenAction) -> Unit
) {
    Column {
        CategorizedProductGrid(
            modifier = Modifier.weight(1f),
            categoryToProductListMap = state.categoryToProductListMap,
            expandedCategories = state.expandedCategories,
            onToggleExpanded = { category ->
                onAction(ShoppingListScreenAction.OnCategoryHeaderClick(category))
            },
            productCard = { product ->
                ShoppingListProductCard(
                    product = product,
                    onChecked = {
                        onAction(
                            ShoppingListScreenAction.OnProductCheckedChange(
                                product,
                                it
                            )
                        )
                    },
                )
            }
        )
        var completedSectionExpanded by remember {
            mutableStateOf(false)
        }
        CategoryCard(
            labelText = UiText.StringResourceId(Res.string.shopping_list_completed_card_label),
            icon = Icons.Default.CheckBox,
            productList = state.completedProducts,
            isExpanded = completedSectionExpanded,
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            onToggleExpanded = { completedSectionExpanded = !completedSectionExpanded },
            productCard = { product ->
                ShoppingListProductCard(
                    product = product,
                    onChecked = {
                        onAction(
                            ShoppingListScreenAction.OnProductCheckedChange(
                                product,
                                it
                            )
                        )
                    }
                )
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShoppingListProductCard(
    product: Product,
    onChecked: (Boolean) -> Unit
) {
    ElevatedCard(
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            val textDecoration =
                if (product.selected) TextDecoration.LineThrough else TextDecoration.None
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = product.name,
                    textDecoration = textDecoration,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 4.dp, end = 12.dp).weight(9f)
                )
                Checkbox(
                    checked = product.selected,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = MaterialTheme.colorScheme.primary,
                        checkmarkColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    onCheckedChange = {
                        onChecked(it)
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            if (product.details.isNotEmpty())
                Text(
                    text = product.details,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    textDecoration = textDecoration,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )
            if (product.quantity != null)
                Text(
                    text = getProductAmountText(product),
                    textDecoration = textDecoration,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                )
        }
    }
}
