package com.macom.shoppingapp.data.source.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Value(
    @Json(name = "actual_price")
    val actualPrice: String?,
    @Json(name = "banner_url")
    val bannerUrl: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "is_express")
    val isExpress: Boolean?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "offer")
    val offer: Int?,
    @Json(name = "offer_price")
    val offerPrice: String?
)