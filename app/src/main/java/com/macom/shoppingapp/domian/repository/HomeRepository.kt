package com.macom.shoppingapp.domian.repository

import com.macom.shoppingapp.app.util.Resource
import com.macom.shoppingapp.domian.model.Banner
import com.macom.shoppingapp.domian.model.Category
import com.macom.shoppingapp.domian.model.Product
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getHomeScreenItems(): Flow<Resource<Triple<Flow<List<Category>>, Flow<List<Banner>>, Flow<List<Product>>>>>
    fun addOrDeleteFavorite(id: Int, favAddState: Boolean): Flow<Resource<Boolean>>
    fun getFavorite(): Flow<Resource<List<Product>>>

}