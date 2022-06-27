package com.macom.shoppingapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.macom.shoppingapp.data.source.local.dao.BannerDao
import com.macom.shoppingapp.data.source.local.dao.CategoryDao
import com.macom.shoppingapp.data.source.local.dao.FavoriteDao
import com.macom.shoppingapp.data.source.local.dao.ProductDao
import com.macom.shoppingapp.data.source.local.entity.BannerEntity
import com.macom.shoppingapp.data.source.local.entity.CategoryEntity
import com.macom.shoppingapp.data.source.local.entity.FavoriteEntity
import com.macom.shoppingapp.data.source.local.entity.ProductEntity

@Database(
    entities = [CategoryEntity::class, BannerEntity::class, ProductEntity::class, FavoriteEntity::class],
    version = 1
)
abstract class ShoppingAppDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val bannerDao: BannerDao
    abstract val productDao: ProductDao
    abstract val favoriteDao: FavoriteDao
}