package com.example.foodie.common.domain.repository

import com.example.foodie.common.data.room.entities.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

interface FavoriteRecipesRepository {
    suspend fun addFavoriteRecipe(recipe: FavoriteRecipe)
    fun getAllRecipes(): Flow<List<FavoriteRecipe>>
    suspend fun removeFavoriteRecipeById(id: String)
    suspend fun findRecipeById(id: String): FavoriteRecipe?
}