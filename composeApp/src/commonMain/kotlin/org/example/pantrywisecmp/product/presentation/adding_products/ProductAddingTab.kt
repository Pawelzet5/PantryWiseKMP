package org.example.pantrywisecmp.product.presentation.adding_products

import org.jetbrains.compose.resources.StringResource
import pantrywisecmp.composeapp.generated.resources.Res
import pantrywisecmp.composeapp.generated.resources.product_suggestion_type_recent_label
import pantrywisecmp.composeapp.generated.resources.product_suggestion_type_search_label
import pantrywisecmp.composeapp.generated.resources.product_suggestion_type_selected_label

enum class ProductAddingTab(val labelRes: StringResource) {
    SELECTED(Res.string.product_suggestion_type_selected_label),
    SEARCH(Res.string.product_suggestion_type_search_label),
    RECENT(Res.string.product_suggestion_type_recent_label)
}