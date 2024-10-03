package com.example.foodie.common.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [SearchQuery::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun searchQueryDao(): SearchQueryDao
}