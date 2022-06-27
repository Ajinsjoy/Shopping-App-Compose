package com.macom.shoppingapp.data.source.local.dao

import androidx.room.*
import com.macom.shoppingapp.data.source.local.entity.FavoriteEntity
import com.macom.shoppingapp.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_table")
    fun getFavorite(): List<FavoriteEntity>

}