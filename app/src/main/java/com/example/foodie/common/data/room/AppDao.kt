package com.example.foodie.common.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchQueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(query: SearchQuery)

    @Query("SELECT * FROM search_queries WHERE `query` = :query LIMIT 1")
    suspend fun findQuery(query: String): SearchQuery?

    @Query("SELECT * FROM search_queries")
    fun getAllQueries(): Flow<List<SearchQuery>>
}

@Dao
interface FavoriteRecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipes)

    @Query("SELECT * FROM favorite")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipes>>

    @Query("DELETE FROM favorite WHERE uri = :uri")
    suspend fun deleteByUri(uri: String)

    @Query("SELECT * FROM favorite WHERE uri = :uri")
    suspend fun getRecipeByUri(uri: String): FavoriteRecipes
}
