package com.example.foodie.common.di

import android.content.Context
import androidx.room.Room
import com.example.foodie.common.data.repository.FavoriteRecipesRepositoryImpl
import com.example.foodie.common.data.repository.SearchHistoryRepositoryImpl
import com.example.foodie.common.data.room.AppDatabase
import com.example.foodie.common.data.room.dao.FavoriteRecipesDao
import com.example.foodie.common.data.room.dao.SearchQueryDao
import com.example.foodie.common.domain.repository.FavoriteRecipesRepository
import com.example.foodie.common.domain.repository.SearchHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

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
    fun provideSearchQueryDao(database: AppDatabase): SearchQueryDao {
        return database.searchQueryDao()
    }

    @Provides
    fun provideFavoriteRecipesDao (database: AppDatabase): FavoriteRecipesDao {
        return database.favoriteRecipesDao()
    }

    @Provides
    fun provideSearchHistoryRepository(
        searchQueryDao: SearchQueryDao,
    ): SearchHistoryRepository {
        return SearchHistoryRepositoryImpl(searchQueryDao)
    }

    @Provides
    fun provideFavoriteRecipesRepository(
        favoriteRecipesDao: FavoriteRecipesDao,
    ): FavoriteRecipesRepository {
        return FavoriteRecipesRepositoryImpl (favoriteRecipesDao)
    }
}