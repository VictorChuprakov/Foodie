package com.example.foodie.common.di

import android.content.Context
import androidx.room.Room
import com.example.foodie.common.data.room.AppDatabase
import com.example.foodie.common.data.room.SearchQueryDao
import com.example.foodie.common.domain.DatabaseRepositoryImpl
import com.example.foodie.common.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "news_database"
        ).build()
    }

    @Provides
    fun provideFavoriteNewsDao(database: AppDatabase): SearchQueryDao {
        return database.searchQueryDao()
    }

    @Provides
    fun provideDatabaseRepository(favoriteNewsDao: SearchQueryDao): DatabaseRepository {
        return DatabaseRepositoryImpl(favoriteNewsDao)
    }
}