package com.macom.shoppingapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.macom.shoppingapp.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM product_table")
    fun getProducts(): Flow<List<ProductEntity>>

    @Query("UPDATE product_table SET favorite = :fav WHERE id = :ids")
    suspend fun favorite(ids: Int, fav: Boolean)

    @Query("SELECT * FROM product_table INNER JOIN favorite_table ON product_table.id=favorite_table.favId")
    fun getFavorite(): Flow<List<ProductEntity>>
}