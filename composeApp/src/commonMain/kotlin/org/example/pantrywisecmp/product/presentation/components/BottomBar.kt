package org.example.pantrywisecmp.product.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.example.pantrywisecmp.product.presentation.navigation.ProductGraphRoutes

private val navigationIconHeight = 24.dp
private val backgroundBoxHeight = navigationIconHeight * 2

data class FancyBottomBarItem(
    val route: ProductGraphRoutes,
    val icon: ImageVector,
    val contentDescription: String
)

@Composable
fun FancyBottomBar(
    modifier: Modifier = Modifier,
    currentRoute: ProductGraphRoutes,
    items: List<FancyBottomBarItem>,
    onNavigationItemClicked: (ProductGraphRoutes) -> Unit,
    color: Color = Color.Black
) {
    require(items.isNotEmpty()) { "BottomBar cannot be empty" }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(backgroundBoxHeight)
                .background(color, RoundedCornerShape(28.dp))
                .padding(9.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach {
                NavigationItem(
                    modifier = Modifier,
                    selected = currentRoute == it.route,
                    icon = it.icon,
                    contentDescription = it.contentDescription,
                    color = color,
                    onClick = { onNavigationItemClicked(it.route) }
                )
            }
        }
    }
}

@Composable
private fun NavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    icon: ImageVector,
    contentDescription: String,
    color: Color = Color.Black,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.drawBehind {
            if (selected)
                drawCircle(color = color, radius = size.minDimension / 1.5f)
        }
    ) {
        Icon(
            icon,
            contentDescription = contentDescription,
            tint = Color.White,
            modifier = Modifier.size(navigationIconHeight)
        )
    }
}