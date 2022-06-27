package com.macom.shoppingapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val actualPrice: String,
    val offerPrice: String,
    val offer: Int,
    val isExpress: Boolean,
    val favorite: Boolean = false
)
