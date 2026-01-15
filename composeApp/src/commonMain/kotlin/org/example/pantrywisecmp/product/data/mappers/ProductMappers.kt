package org.example.pantrywisecmp.product.data.mappers

import org.example.pantrywisecmp.product.data.database.ProductEntity
import org.example.pantrywisecmp.product.domain.Product

fun Product.toProductEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    details = details,
    quantity = quantity,
    productUnit =productUnit,
    category = category,
    productStatus = productStatus,
    expirationDate = expirationDate
)

fun ProductEntity.toProduct(selected: Boolean = false): Product = Product(
    id = id,
    name = name,
    details = details,
    quantity = quantity,
    productUnit = productUnit,
    category = category,
    productStatus = productStatus,
    expirationDate = expirationDate,
    selected = selected
)