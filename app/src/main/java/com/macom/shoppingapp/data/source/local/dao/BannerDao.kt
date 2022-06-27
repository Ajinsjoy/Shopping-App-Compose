package com.macom.shoppingapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.macom.shoppingapp.data.source.local.entity.BannerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanners(banners : List<BannerEntity>)

    @Query("SELECT * FROM banner_table")
    fun getBanners() : Flow<List<BannerEntity>>

}