package com.example.foodie.common.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [SearchQuery::class, FavoriteRecipes::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchQueryDao(): SearchQueryDao
    abstract fun favoriteRecipesDao(): FavoriteRecipesDao
}
