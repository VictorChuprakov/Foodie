package com.example.foodie.common.domain.repository

import com.example.foodie.common.data.room.FavoriteRecipes
import com.example.foodie.common.data.room.SearchQuery
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    // Методы для работы с SearchQuery
    suspend fun insertSearchQuery(query: SearchQuery)
    suspend fun findQuery(query: String): SearchQuery?
    fun getAllSearchQueries(): Flow<List<SearchQuery>>

    // Методы для работы с FavoriteRecipes
    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipes)
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipes>>
    suspend fun deleteFavoriteRecipe(uri: String)
    suspend fun getRecipeByUri(uri: String): FavoriteRecipes?
}
