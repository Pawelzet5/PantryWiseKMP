package org.example.pantrywisecmp.product.presentation.adding_products

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.pantrywisecmp.core.presentation.components.ActionIcon
import org.example.pantrywisecmp.core.presentation.components.SwipeableItemWithActions
import org.example.pantrywisecmp.product.presentation.model.ProductDraft
import org.example.pantrywisecmp.product.domain.ProductSuggestion
import org.example.pantrywisecmp.product.presentation.helper.DateTimeHelper
import org.example.pantrywisecmp.product.presentation.product_input.ProductInputAction
import org.example.pantrywisecmp.product.presentation.product_input.ProductInputScreenRoot
import org.example.pantrywisecmp.product.presentation.product_input.ProductInputViewModel
import org.example.pantrywisecmp.product.presentation.util.getLabel
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import pantrywisecmp.composeapp.generated.resources.Res
import pantrywisecmp.composeapp.generated.resources.add_product
import pantrywisecmp.composeapp.generated.resources.bg_doodle_1
import pantrywisecmp.composeapp.generated.resources.bg_doodle_2
import pantrywisecmp.composeapp.generated.resources.bg_doodle_3_v2
import pantrywisecmp.composeapp.generated.resources.common_delete
import pantrywisecmp.composeapp.generated.resources.ic_check_circle
import pantrywisecmp.composeapp.generated.resources.ic_circle_add
import pantrywisecmp.composeapp.generated.resources.ic_delete
import pantrywisecmp.composeapp.generated.resources.product_input_search_placeholder
import pantrywisecmp.composeapp.generated.resources.product_input_view_add_products_to_inventory
import pantrywisecmp.composeapp.generated.resources.product_suggestion_type_recent_label
import pantrywisecmp.composeapp.generated.resources.product_suggestion_type_search_label
import pantrywisecmp.composeapp.generated.resources.product_suggestion_type_selected_label
import pantrywisecmp.composeapp.generated.resources.recent_product_list_empty_message
import pantrywisecmp.composeapp.generated.resources.selected_product_list_empty_message
import pantrywisecmp.composeapp.generated.resources.suggested_product_list_empty_message_add_product_text
import pantrywisecmp.composeapp.generated.resources.suggested_product_list_empty_message_content
import pantrywisecmp.composeapp.generated.resources.suggested_product_list_empty_message_headline
import kotlin.uuid.ExperimentalUuidApi


@Composable
fun SearchAndSelectProductsScreenRoot(viewModel: SearchAndSelectProductsViewModel = koinViewModel()) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    val selectedProduct by viewModel.selectedProduct.collectAsStateWithLifecycle()

    SearchAndSelectProductsScreen(state, viewModel::onAction)

    selectedProduct?.let {
        SelectedProductDialog(productDraft = it, onAction = viewModel::onAction)
    }
}

@Composable
private fun SearchAndSelectProductsScreen(
    state: SearchAndSelectProductsState,
    onAction: (SearchAndSelectProductsAction) -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize()
    ) {
        AddingProductTab(
            state = state,
            onSelectedProductClick = {
                onAction(
                    SearchAndSelectProductsAction.OnProductClick(it)
                )
            },
            onDeleteSelectedProduct = {
                onAction(
                    SearchAndSelectProductsAction.OnDeleteProduct(it)
                )
            },
            onProductSuggestionClick = {
                onAction(
                    SearchAndSelectProductsAction.OnProductSuggestionClick(it)
                )
            },
            onAddManually = {
                onAction(SearchAndSelectProductsAction.OnAddProductManually)
            },
            onQueryInput = {
                onAction(SearchAndSelectProductsAction.OnQueryTextInput(it))
            },
            modifier = Modifier.fillMaxSize()
        )

        if (state.selectedProducts.isNotEmpty())
            Button(
                onClick = { onAction(SearchAndSelectProductsAction.OnAddProductList) },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(stringResource(Res.string.product_input_view_add_products_to_inventory))
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectedProductDialog(
    productDraft: ProductDraft,
    onAction: (SearchAndSelectProductsAction) -> Unit
) {
    val productInputViewModel = koinViewModel<ProductInputViewModel>()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(productDraft) {
        productInputViewModel.onAction(ProductInputAction.OnProductUpdated(productDraft))
    }

    ModalBottomSheet(
        onDismissRequest = { onAction(SearchAndSelectProductsAction.OnCancelProductInputClick) },
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        ProductInputScreenRoot(
            modifier = Modifier.fillMaxWidth(),
            confirmInputTextStringRes = Res.string.add_product,
            viewModel = productInputViewModel,
            onCancel = {
                onAction(SearchAndSelectProductsAction.OnCancelProductInputClick)
            },
            onSubmitInput = {
                onAction(SearchAndSelectProductsAction.OnConfirmProductInputClick(it))
            },
        )
    }
}

@Composable
private fun AddingProductTab(
    state: SearchAndSelectProductsState,
    onSelectedProductClick: (ProductDraft) -> Unit,
    onDeleteSelectedProduct: (ProductDraft) -> Unit,
    onProductSuggestionClick: (ProductSuggestion) -> Unit,
    onAddManually: () -> Unit,
    onQueryInput: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabItems = ProductAddingTab.entries
    var selectedTabIndex by remember {
        mutableIntStateOf(1)
    }
    val pagerState = rememberPagerState(
        initialPage = selectedTabIndex,
        pageCount = { tabItems.size },
    )
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }
    Column(modifier.fillMaxWidth()) {
        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(
                            text = stringResource(item.labelRes),
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            modifier = Modifier
                        )
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) { index ->
            when (tabItems[index]) {
                ProductAddingTab.SELECTED -> SelectedProductsTabContent(
                    selectedProductList = state.selectedProducts,
                    onSelectedProductClick = onSelectedProductClick,
                    onDeleteSelectedProduct = onDeleteSelectedProduct
                )

                ProductAddingTab.SEARCH -> SuggestedProductsTabContent(
                    queryText = state.queryText,
                    productSuggestionList = state.suggestedProducts,
                    onProductSuggestionClick = {
                        onProductSuggestionClick(it)
                        selectedTabIndex = ProductAddingTab.SELECTED.ordinal
                    },
                    onAddManually = onAddManually,
                    onQueryInput = onQueryInput
                )

                ProductAddingTab.RECENT -> RecentProductsTabContent(
                    recentProductList = state.recentProducts,
                    onProductSuggestionClick = {
                        onProductSuggestionClick(it)
                        selectedTabIndex = ProductAddingTab.SELECTED.ordinal
                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalUuidApi::class)
@Composable
private fun SelectedProductsTabContent(
    selectedProductList: List<ProductDraft>,
    onSelectedProductClick: (ProductDraft) -> Unit,
    onDeleteSelectedProduct: (ProductDraft) -> Unit
) {
    AnimatedContent(
        targetState = selectedProductList.isEmpty()
    ) { isListEmpty ->
        if (isListEmpty)
            EmptySelectedTabMessage()
        else
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(selectedProductList, key = { it.tempId }) {
                    SelectedProductRow(
                        modifier = Modifier.animateItem(),
                        productDraft = it,
                        onClick = { onSelectedProductClick(it) },
                        onDelete = { onDeleteSelectedProduct(it) }
                    )
                }
            }
    }
}

@Composable
private fun SelectedProductRow(
    modifier: Modifier = Modifier,
    productDraft: ProductDraft,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val quantityWithUnitText = if (!productDraft.quantity?.toString().isNullOrEmpty())
        "${productDraft.quantity} " + productDraft.productUnit.getLabel().asString()
    else ""

    SwipeableItemWithActions(
        isRevealed = productDraft.isDeleteOptionRevealed,
        actions = {
            ActionIcon(
                onClick = onDelete,
                backgroundColor = MaterialTheme.colorScheme.error,
                icon = vectorResource(Res.drawable.ic_delete),
                label = stringResource(Res.string.common_delete)
            )
        },
        modifier = modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .clickable { onClick() }
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
        ) {
            if (productDraft.name.isNotEmpty())
                Text(
                    text = productDraft.name,
                    style = MaterialTheme.typography.bodyLarge
                )
            if (productDraft.details.isNotEmpty())
                Text(text = productDraft.details)
            if (quantityWithUnitText.isNotEmpty())
                Text(text = quantityWithUnitText)
            productDraft.expirationDate?.let {
                Text(text = DateTimeHelper.formatTimestampToDayMonthYear(it))
            }
        }
    }
}

@Composable
private fun EmptySelectedTabMessage() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(Res.string.selected_product_list_empty_message),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(32.dp)
        )
        Box(
            Modifier.fillMaxSize(),
        ) {
            Image(
                imageVector = vectorResource(Res.drawable.bg_doodle_3_v2),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}


@Composable
private fun SuggestedProductsTabContent(
    queryText: String,
    productSuggestionList: List<ProductSuggestion>,
    onAddManually: () -> Unit,
    onProductSuggestionClick: (ProductSuggestion) -> Unit,
    onQueryInput: (String) -> Unit
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedContent(
            targetState = productSuggestionList.isEmpty()
        ) { isListEmpty ->
            if (isListEmpty)
                NoProductsFoundMessage(onAddProductManually = { onAddManually() })
            else
                ProductSuggestionList(
                    modifier = Modifier.fillMaxSize(),
                    list = productSuggestionList,
                    onItemClick = onProductSuggestionClick
                )
        }
        SearchBar(
            queryText,
            onTextChanged = onQueryInput,
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 4.dp)
        )
    }
}

@Composable
private fun ProductSuggestionList(
    modifier: Modifier = Modifier,
    list: List<ProductSuggestion>,
    onItemClick: (ProductSuggestion) -> Unit
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier
    ) {
        items(list) {
            ProductSuggestionRow(
                productSuggestion = it,
                onClick = {
                    onItemClick(it)
                }
            )
        }
    }
}

@Composable
private fun NoProductsFoundMessage(onAddProductManually: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(top = 100.dp)
    ) {
        Text(
            text = stringResource(Res.string.suggested_product_list_empty_message_headline),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = stringResource(Res.string.suggested_product_list_empty_message_content),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(Res.string.suggested_product_list_empty_message_add_product_text),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(8.dp).clickable {
                onAddProductManually()
            }
        )
    }
}

@Composable
private fun ProductSuggestionRow(
    productSuggestion: ProductSuggestion,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(vertical = 16.dp)
    ) {
        Icon(
            imageVector = vectorResource(
                if (productSuggestion.isAdded)
                    Res.drawable.ic_check_circle
                else
                    Res.drawable.ic_circle_add
            ),
            tint = Color.Unspecified,
            contentDescription = null
        )
        Text(productSuggestion.name)
    }
}

@Composable
private fun RecentProductsTabContent(
    recentProductList: List<ProductSuggestion>,
    onProductSuggestionClick: (ProductSuggestion) -> Unit
) {
    AnimatedContent(
        targetState = recentProductList.isEmpty()
    ) { isListEmpty ->
        if (isListEmpty)
            EmptyRecentTabMessage()
        else
            ProductSuggestionList(
                modifier = Modifier.fillMaxSize(),
                list = recentProductList,
                onItemClick = onProductSuggestionClick
            )
    }
}


@Composable
private fun EmptyRecentTabMessage() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(Res.string.recent_product_list_empty_message),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(32.dp)
        )
        Box(
            Modifier.fillMaxSize(),
        ) {
            Image(
                imageVector = vectorResource(Res.drawable.bg_doodle_2),
                contentDescription = null,
                modifier = Modifier.align(Alignment.TopEnd)
            )
            Image(
                imageVector = vectorResource(Res.drawable.bg_doodle_1),
                contentDescription = null,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
private fun SearchBar(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        placeholder = {
            Text(stringResource(Res.string.product_input_search_placeholder))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(28.dp),
        modifier = modifier.fillMaxWidth()
    )
}

enum class ProductAddingTab(val labelRes: StringResource) {
    SELECTED(Res.string.product_suggestion_type_selected_label),
    SEARCH(Res.string.product_suggestion_type_search_label),
    RECENT(Res.string.product_suggestion_type_recent_label)
}