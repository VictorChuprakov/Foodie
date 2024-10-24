package com.example.foodie.common.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodie.common.data.room.dao.FavoriteRecipesDao
import com.example.foodie.common.data.room.dao.SearchQueryDao
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.data.room.entities.SearchHistory


@Database(entities = [SearchHistory::class, FavoriteRecipe::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchQueryDao(): SearchQueryDao
    abstract fun favoriteRecipesDao(): FavoriteRecipesDao
}
