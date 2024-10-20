package com.example.foodie.common.data.repository

import com.example.foodie.common.data.room.FavoriteRecipes
import com.example.foodie.common.data.room.FavoriteRecipesDao
import com.example.foodie.common.data.room.SearchQuery
import com.example.foodie.common.data.room.SearchQueryDao
import com.example.foodie.common.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow


class DatabaseRepositoryImpl(
    private val searchQueryDao: SearchQueryDao,
    private val favoriteRecipesDao: FavoriteRecipesDao
) : DatabaseRepository {

    // Реализация методов для SearchQuery
    override suspend fun insertSearchQuery(query: SearchQuery) {
        searchQueryDao.insert(query)
    }

    override suspend fun findQuery(query: String): SearchQuery? {
        return searchQueryDao.findQuery(query)
    }

    override fun getAllSearchQueries(): Flow<List<SearchQuery>> {
        return searchQueryDao.getAllQueries()
    }

    // Реализация методов для FavoriteRecipes
    override suspend fun insertFavoriteRecipe(recipe: FavoriteRecipes) {
        favoriteRecipesDao.insertFavoriteRecipe(recipe)
    }

    override fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipes>> {
        return favoriteRecipesDao.getAllFavoriteRecipes()
    }

    override suspend fun deleteFavoriteRecipe(uri: String) {
        favoriteRecipesDao.deleteByUri(uri)
    }

    override suspend fun getRecipeByUri(uri: String): FavoriteRecipes? {
        return favoriteRecipesDao.getRecipeByUri(uri)
    }
}
