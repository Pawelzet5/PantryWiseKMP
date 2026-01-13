package org.example.pantrywisecmp.product.domain

enum class ProductUnit {
    // Weight-based units
    GRAM,
    KILOGRAM,
    MILLIGRAM,
    POUND,
    OUNCE,

    // Volume-based units
    MILLILITER,
    LITER,
    TEASPOON,
    TABLESPOON,
    CUP,
    PINT,
    QUART,
    GALLON,

    // Countable items
    PIECE,
    DOZEN,

    // Packaged items
    PACK,
    BOTTLE,
    CAN,
    JAR,
    BAG,
    BOX;

    companion object {
        fun getStorageUnits() = listOf(
            GRAM,
            KILOGRAM,
            MILLILITER,
            LITER,
            PIECE,
            PACK,
            CAN,
            JAR,
            BAG,
            BOX
        )
    }
}