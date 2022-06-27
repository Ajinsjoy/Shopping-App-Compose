package com.macom.shoppingapp.data.source.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeData(
    @Json(name = "type")
    val type: String?,
    @Json(name = "values")
    val values: List<Value>?
)