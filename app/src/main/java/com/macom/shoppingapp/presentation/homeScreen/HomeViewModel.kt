package com.macom.shoppingapp.presentation.homeScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macom.shoppingapp.app.util.Resource
import com.macom.shoppingapp.domian.model.Banner

import com.macom.shoppingapp.domian.model.Category
import com.macom.shoppingapp.domian.model.Product
import com.macom.shoppingapp.domian.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    var mainUiState = mutableStateOf(MainUiState())
        private set

    init {
        getHomeScreenItems()
    }

    private fun getHomeScreenItems() {
        viewModelScope.launch {
            homeRepository.getHomeScreenItems().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        mainUiState.value = mainUiState.value.copy(loading = true)
                    }
                    is Resource.Success -> {
                        mainUiState.value = mainUiState.value.copy(loading = false)

                        handleCategory(result.value.first)
                        handleBanners(result.value.second)
                        handleProducts(result.value.third)
                    }
                    is Resource.Error -> {
                        mainUiState.value =
                            mainUiState.value.copy(loading = false, error = result.error)
                    }
                }
            }
        }
    }

    private fun handleCategory(categoriesFlow: Flow<List<Category>>) {
        viewModelScope.launch {
            categoriesFlow.collect { categories ->
                mainUiState.value = mainUiState.value.copy(categories = categories)
            }
        }
    }

    private fun handleBanners(bannersFlow: Flow<List<Banner>>) {
        viewModelScope.launch {
            bannersFlow.collect { banners ->
                mainUiState.value = mainUiState.value.copy(banners = banners)

            }
        }
    }

    private fun handleProducts(productsFlow: Flow<List<Product>>) {
        viewModelScope.launch {
            productsFlow.collect { products ->
                mainUiState.value = mainUiState.value.copy(products = products)
            }
        }
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