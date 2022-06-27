package com.macom.shoppingapp.app.di

import android.app.Application
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.macom.shoppingapp.data.source.local.ShoppingAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideShoppingAppDatabase(application: Application) =
        Room.databaseBuilder(application, ShoppingAppDatabase::class.java, "shopping_app_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCategoryDao(shoppingAppDatabase: ShoppingAppDatabase) =
        shoppingAppDatabase.categoryDao

    @Provides
    @Singleton
    fun provideBannerDao(shoppingAppDatabase: ShoppingAppDatabase) = shoppingAppDatabase.bannerDao

    @Provides
    @Singleton
    fun provideProductDao(shoppingAppDatabase: ShoppingAppDatabase) = shoppingAppDatabase.productDao

    @Provides
    @Singleton
    fun provideFavoriteDao(shoppingAppDatabase: ShoppingAppDatabase) =
        shoppingAppDatabase.favoriteDao


    @Provides
    @Singleton
    fun provideSharedPreference(application: Application) =
        PreferenceManager.getDefaultSharedPreferences(application)
}