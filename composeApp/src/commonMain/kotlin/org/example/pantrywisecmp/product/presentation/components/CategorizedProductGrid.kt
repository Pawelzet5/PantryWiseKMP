package org.example.pantrywisecmp.product.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductCategory
import org.example.pantrywisecmp.product.presentation.util.getIcon
import org.example.pantrywisecmp.product.presentation.util.getLabel
import org.jetbrains.compose.resources.stringResource
import pantrywisecmp.composeapp.generated.resources.Res
import pantrywisecmp.composeapp.generated.resources.product_category_with_count

/**
 * A component designed for displaying product lists grouped by their categories
 */
@Composable
fun CategorizedProductGrid(
    modifier: Modifier = Modifier,
    categoryToProductListMap: Map<ProductCategory, List<Product>> = emptyMap(),
    expandedCategories: Set<ProductCategory> = emptySet(),
    onToggleExpanded: (ProductCategory) -> Unit,
    stickyHeader: (@Composable () -> Unit)? = null,
    productCard: @Composable (Product) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        stickyHeader?.let {
            stickyHeader { it() }
        }
        items(categoryToProductListMap.keys.toList(), key = { it.ordinal }) { category ->
            CategoryCard(
                labelText = category.getLabel(),
                icon = category.getIcon(),
                productList = categoryToProductListMap[category],
                isExpanded = expandedCategories.contains(category),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                onToggleExpanded = { onToggleExpanded(category) },
                productCard = productCard
            )
        }
    }
}

@Composable
fun CategoryCard(
    labelText: UiText,
    icon: ImageVector,
    productList: List<Product>?,
    isExpanded: Boolean,
    containerColor: Color,
    onToggleExpanded: () -> Unit,
    productCard: @Composable (Product) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        ProductCategoryHeader(
            modifier = Modifier
                .padding(0.dp),
            labelText = labelText,
            icon = icon,
            productCount = productList?.size ?: 0,
            isExpanded = isExpanded,
            onToggleExpanded = onToggleExpanded
        )
        AnimatedVisibility(
            visible = (isExpanded && !productList.isNullOrEmpty())
        ) {
            ProductsGrid(
                productList ?: emptyList(),
                productCard = productCard
            )
        }
    }
}

@Composable
fun ProductsGrid(
    products: List<Product>,
    productCard: @Composable (Product) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .heightIn(max = 300.dp),
        contentPadding = PaddingValues(4.dp),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products, key = { it.id }) { product ->
            productCard(product)
        }
    }
}

@Composable
fun ProductCategoryHeader(
    modifier: Modifier = Modifier,
    labelText: UiText,
    icon: ImageVector,
    productCount: Int,
    isExpanded: Boolean,
    onToggleExpanded: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onToggleExpanded() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )

        val headerText = stringResource(
            Res.string.product_category_with_count,
            labelText.asString(),
            productCount
        )
        Text(
            text = headerText,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = if (isExpanded) "Collapse" else "Expand",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(24.dp)
        )
    }
}