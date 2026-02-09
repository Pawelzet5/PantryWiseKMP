package org.example.pantrywisecmp.product.presentation.util

import androidx.compose.runtime.Composable
import org.example.pantrywisecmp.core.domain.UiText
import org.example.pantrywisecmp.product.domain.Product
import org.example.pantrywisecmp.product.domain.ProductUnit
import pantrywisecmp.composeapp.generated.resources.*

fun ProductUnit.getLabel(): UiText = when (this) {
    ProductUnit.GRAM -> Res.string.product_unit_gram
    ProductUnit.KILOGRAM -> Res.string.product_unit_kilogram
    ProductUnit.MILLIGRAM -> Res.string.product_unit_milligram
    ProductUnit.POUND -> Res.string.product_unit_pound
    ProductUnit.OUNCE -> Res.string.product_unit_ounce
    ProductUnit.MILLILITER -> Res.string.product_unit_milliliter
    ProductUnit.LITER -> Res.string.product_unit_liter
    ProductUnit.TEASPOON -> Res.string.product_unit_teaspoon
    ProductUnit.TABLESPOON -> Res.string.product_unit_tablespoon
    ProductUnit.CUP -> Res.string.product_unit_cup
    ProductUnit.PINT -> Res.string.product_unit_pint
    ProductUnit.QUART -> Res.string.product_unit_quart
    ProductUnit.GALLON -> Res.string.product_unit_gallon
    ProductUnit.PIECE -> Res.string.product_unit_piece
    ProductUnit.DOZEN -> Res.string.product_unit_dozen
    ProductUnit.PACK -> Res.string.product_unit_pack
    ProductUnit.BOTTLE -> Res.string.product_unit_bottle
    ProductUnit.CAN -> Res.string.product_unit_can
    ProductUnit.JAR -> Res.string.product_unit_jar
    ProductUnit.BAG -> Res.string.product_unit_bag
    ProductUnit.BOX -> Res.string.product_unit_box
}.let { UiText.StringResourceId(it) }

fun ProductUnit.getShortLabel(): UiText = when (this) {
    ProductUnit.GRAM -> Res.string.product_unit_short_gram
    ProductUnit.KILOGRAM -> Res.string.product_unit_short_kilogram
    ProductUnit.MILLIGRAM -> Res.string.product_unit_short_milligram
    ProductUnit.POUND -> Res.string.product_unit_short_pound
    ProductUnit.OUNCE -> Res.string.product_unit_short_ounce
    ProductUnit.MILLILITER -> Res.string.product_unit_short_milliliter
    ProductUnit.TEASPOON -> Res.string.product_unit_short_teaspoon
    ProductUnit.TABLESPOON -> Res.string.product_unit_short_tablespoon
    else -> null
}?.let { UiText.StringResourceId(it) }?: getLabel()

@Composable
fun getProductAmountText(product: Product): String {
    val unitText = product.productUnit.getShortLabel()
    return "${product.quantity} ${unitText.asString()}"
}