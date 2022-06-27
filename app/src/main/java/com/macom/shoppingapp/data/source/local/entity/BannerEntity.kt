package com.macom.shoppingapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banner_table")
data class BannerEntity(
    @PrimaryKey
    val id : Int,
    val image : String
)
