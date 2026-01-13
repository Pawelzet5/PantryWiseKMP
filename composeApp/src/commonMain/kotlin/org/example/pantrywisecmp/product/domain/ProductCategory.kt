package org.example.pantrywisecmp.product.domain

enum class ProductCategory {
    DAIRY_EGGS,
    BEVERAGES,
    BAKERY,
    MEAT_SEAFOOD,
    PRODUCE,
    GRAINS_CEREALS,
    CANNED_GOODS,
    CONDIMENTS_SAUCES,
    SNACKS_SWEETS,
    SPICES_SEASONINGS,
    OILS_FATS,
    FROZEN_FOODS,
    PANTRY_STAPLES,
    OTHER;

    companion object {
        fun getMainCategories() = listOf(
            DAIRY_EGGS,
            BEVERAGES,
            BAKERY,
            MEAT_SEAFOOD,
            PRODUCE,
            GRAINS_CEREALS,
            CANNED_GOODS,
            CONDIMENTS_SAUCES,
            SNACKS_SWEETS,
            SPICES_SEASONINGS,
            OILS_FATS,
            FROZEN_FOODS,
            PANTRY_STAPLES
        )
    }
}
