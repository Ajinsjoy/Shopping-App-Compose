package com.macom.shoppingapp.presentation.homeScreen

import com.macom.shoppingapp.domian.model.Banner
import com.macom.shoppingapp.domian.model.Category
import com.macom.shoppingapp.domian.model.Product


data class MainUiState(
    val loading : Boolean = false,
    val categories : List<Category> = emptyList(),
    val banners : List<Banner> = emptyList(),
    val products : List<Product> = emptyList(),
    val error : String? = null
)
