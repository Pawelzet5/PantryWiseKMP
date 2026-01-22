package org.example.pantrywisecmp.product.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.product.domain.ProductCategory
import pantrywisecmp.composeapp.generated.resources.*


/**
 * Extension function to get the appropriate Material Icon for a ProductCategory
 * This keeps the enum clean while providing UI functionality
 */
fun ProductCategory.getIcon(): ImageVector {
    return when (this) {
        ProductCategory.DAIRY_EGGS -> Icons.Default.Egg
        ProductCategory.BEVERAGES -> Icons.Default.LocalDrink
        ProductCategory.BAKERY -> Icons.Default.BreakfastDining
        ProductCategory.MEAT_SEAFOOD -> Icons.Default.Restaurant
        ProductCategory.PRODUCE -> Icons.Default.Eco
        ProductCategory.GRAINS_CEREALS -> Icons.Default.Grain
        ProductCategory.CANNED_GOODS -> Icons.Default.Inventory2
        ProductCategory.CONDIMENTS_SAUCES -> Icons.Default.Restaurant
        ProductCategory.SNACKS_SWEETS -> Icons.Default.Cake
        ProductCategory.SPICES_SEASONINGS -> Icons.Default.RestaurantMenu
        ProductCategory.OILS_FATS -> Icons.Default.Opacity
        ProductCategory.FROZEN_FOODS -> Icons.Default.AcUnit
        ProductCategory.PANTRY_STAPLES -> Icons.Default.Inventory
        ProductCategory.OTHER -> Icons.Default.Inventory
    }
}

fun ProductCategory.getLabel(): UiText = when (this) {
    ProductCategory.DAIRY_EGGS -> Res.string.category_dairy_eggs
    ProductCategory.BEVERAGES -> Res.string.category_beverages
    ProductCategory.BAKERY -> Res.string.category_bakery
    ProductCategory.MEAT_SEAFOOD -> Res.string.category_meat_seafood
    ProductCategory.PRODUCE -> Res.string.category_produce
    ProductCategory.GRAINS_CEREALS -> Res.string.category_grains_cereals
    ProductCategory.CANNED_GOODS -> Res.string.category_canned_goods
    ProductCategory.CONDIMENTS_SAUCES -> Res.string.category_condiments_sauces
    ProductCategory.SNACKS_SWEETS -> Res.string.category_snacks_sweets
    ProductCategory.SPICES_SEASONINGS -> Res.string.category_spices_seasonings
    ProductCategory.OILS_FATS -> Res.string.category_oils_fats
    ProductCategory.FROZEN_FOODS -> Res.string.category_frozen_foods
    ProductCategory.PANTRY_STAPLES -> Res.string.category_pantry_staples
    ProductCategory.OTHER -> Res.string.category_other
}.let { UiText.StringResourceId(it) }