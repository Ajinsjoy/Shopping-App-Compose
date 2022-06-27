package com.macom.shoppingapp.presentation.favorite

import com.macom.shoppingapp.domian.model.Product

data class FavoriteUiState(
    val products: List<Product> = emptyList(),
    val error: String? = null
)
