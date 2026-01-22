package org.example.pantrywisecmp.product.data

import org.example.pantrywisecmp.product.domain.*
import kotlin.time.Clock.System.now
import kotlin.time.Duration.Companion.days

object MockDataHelper {
    private val nowPlusDays = { days: Long ->
        (now() + days.days).toEpochMilliseconds()
    }

    fun getMockProductList() = listOf(
        Product(
            id = 1,
            name = "Milk",
            details = "3.2%",
            quantity = 2.0,
            productUnit = ProductUnit.LITER,
            category = ProductCategory.BEVERAGES,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 2,
            name = "Bread",
            details = "",
            quantity = 1.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.BAKERY,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 3,
            name = "Burger Buns",
            details = "",
            quantity = 4.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.BAKERY,
            expirationDate = nowPlusDays(1)
        ),
        Product(
            id = 4,
            name = "Grahamki",
            details = "",
            quantity = 5.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.BAKERY,
            expirationDate = nowPlusDays(2)
        ),
        Product(
            id = 6,
            name = "Apples",
            details = "",
            quantity = 6.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.PRODUCE,
            expirationDate = nowPlusDays(14)
        ),
        Product(
            id = 7,
            name = "Chicken Breast",
            details = "",
            quantity = 500.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.MEAT_SEAFOOD,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 8,
            name = "Rice",
            details = "",
            quantity = 1.0,
            productUnit = ProductUnit.KILOGRAM,
            category = ProductCategory.GRAINS_CEREALS
            // bez expirationDate = null domyślnie
        ),
        Product(
            id = 9,
            name = "Olive Oil",
            details = "",
            quantity = 250.0,
            productUnit = ProductUnit.MILLILITER,
            category = ProductCategory.OILS_FATS
        ),
        Product(
            id = 10,
            name = "Salt",
            details = "",
            quantity = 100.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.SPICES_SEASONINGS
        ),
        Product(
            id = 11,
            name = "Eggs",
            details = "",
            quantity = 12.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(30)
        ),
        Product(
            id = 12,
            name = "Cheese",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(8)
        ),
        Product(
            id = 13,
            name = "Mozzarella",
            details = "Fior di Latte",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 14,
            name = "Mozzarella",
            details = "Lidlowa",
            quantity = 2.0,
            productUnit = ProductUnit.BAG,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(3)
        ),
        Product(
            id = 15,
            name = "Cheddar",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(14)
        ),
        Product(
            id = 16,
            name = "Gouda",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(14)
        ),
        Product(
            id = 17,
            name = "Monterey Jack",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(-8)
        ),
        Product(
            id = 18,
            name = "Burrata",
            details = "",
            quantity = 200.0,
            productUnit = ProductUnit.GRAM,
            category = ProductCategory.DAIRY_EGGS,
            expirationDate = nowPlusDays(2)
        ),
        Product(
            id = 19,
            name = "Tomatoes",
            details = "Włoskie Mutti",
            quantity = 4.0,
            productUnit = ProductUnit.PIECE,
            category = ProductCategory.PRODUCE,
            expirationDate = nowPlusDays(6)
        )
    )
}
