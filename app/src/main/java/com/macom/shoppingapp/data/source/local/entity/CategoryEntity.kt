package com.macom.shoppingapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategoryEntity(
    @PrimaryKey
    val id : Int,
    val name : String,
    val image : String
)
