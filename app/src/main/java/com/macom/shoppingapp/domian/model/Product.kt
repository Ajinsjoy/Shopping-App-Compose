package com.macom.shoppingapp.domian.model

data class Product(
    val id : Int,
    val name : String,
    val image : String,
    val actualPrice : String,
    val offerPrice : String,
    val offer : Int,
    val isExpress : Boolean,
    val favorite:Boolean
)
