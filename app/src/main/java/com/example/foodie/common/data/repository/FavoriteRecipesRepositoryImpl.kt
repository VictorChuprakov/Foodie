package com.example.foodie.common.data.repository

import com.example.foodie.common.data.room.dao.FavoriteRecipesDao
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.domain.repository.FavoriteRecipesRepository
import kotlinx.coroutines.flow.Flow

class FavoriteRecipesRepositoryImpl(private val favoriteRecipesDao: FavoriteRecipesDao) : FavoriteRecipesRepository {
    override suspend fun addFavoriteRecipe(recipe: FavoriteRecipe) =
        favoriteRecipesDao.insertFavoriteRecipe(recipe)

    override fun getAllRecipes(): Flow<List<FavoriteRecipe>> =
        favoriteRecipesDao.getAllFavoriteRecipes()

    override suspend fun removeFavoriteRecipe(uri: String) =
        favoriteRecipesDao.deleteByUri(uri)

    override suspend fun findRecipeById(id: String): FavoriteRecipe? =
        favoriteRecipesDao.getRecipeByUri(id)
}
