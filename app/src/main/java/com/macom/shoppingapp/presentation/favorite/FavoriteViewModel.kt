package com.macom.shoppingapp.presentation.favorite

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macom.shoppingapp.app.util.Resource
import com.macom.shoppingapp.domian.model.Product
import com.macom.shoppingapp.domian.repository.HomeRepository
import com.macom.shoppingapp.presentation.homeScreen.MainUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {

    var favoriteUiState = mutableStateOf(FavoriteUiState())
        private set

    init {

        getFavoriteItem()
    }

    private fun getFavoriteItem() = viewModelScope.launch {
        homeRepository.getFavorite().collect {
            when (it) {
                is Resource.Error -> Unit
                is Resource.Loading -> Unit
                is Resource.Success -> handleFavoriteSuccess(it.value)
            }
        }
    }

    private fun handleFavoriteSuccess(product: List<Product>) {
        favoriteUiState.value = favoriteUiState.value.copy(products = product)
    }

    fun addFavorite(id: Int, favAddState: Boolean) {
        viewModelScope.launch {
            homeRepository.addOrDeleteFavorite(id, favAddState).collect {
                when (it) {
                    is Resource.Success -> Unit
                    else -> Unit
                }
            }

        }
    }

}