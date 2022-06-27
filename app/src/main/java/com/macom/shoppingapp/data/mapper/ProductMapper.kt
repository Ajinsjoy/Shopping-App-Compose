package com.macom.shoppingapp.data.mapper

import com.macom.shoppingapp.data.source.local.entity.ProductEntity
import com.macom.shoppingapp.data.source.remote.dto.Value
import com.macom.shoppingapp.domian.model.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        image = image,
        actualPrice = actualPrice,
        offerPrice = offerPrice,
        offer = offer,
        isExpress = isExpress,
        favorite = favorite
    )
}

fun Value.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = id ?: 0,
        name = name ?: "",
        image = image ?: "",
        actualPrice = actualPrice ?: "",
        offerPrice = offerPrice ?: "",
        offer = offer ?: 0,
        isExpress = isExpress ?: true,
    )
}