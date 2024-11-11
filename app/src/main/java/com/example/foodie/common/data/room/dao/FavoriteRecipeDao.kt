package com.example.foodie.common.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipe")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipe>>

    @Query("DELETE FROM favorite_recipe WHERE uri = :uri")
    suspend fun deleteById(uri: String)

    @Query("SELECT * FROM favorite_recipe WHERE uri = :uri")
    suspend fun getRecipeById(uri: String): FavoriteRecipe
}