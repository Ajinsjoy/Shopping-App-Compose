package com.macom.shoppingapp.data.mapper

import com.macom.shoppingapp.data.source.local.entity.BannerEntity
import com.macom.shoppingapp.data.source.remote.dto.Value
import com.macom.shoppingapp.domian.model.Banner

fun BannerEntity.toBanner(): Banner {
    return Banner(
        id = id, image = image
    )
}
fun Value.toBannerEntity(): BannerEntity {
    return BannerEntity(
        id = id ?: 0,
        image = bannerUrl ?: ""
    )
}