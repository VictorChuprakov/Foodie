package com.example.foodie.favorite_details.ui

import androidx.lifecycle.ViewModel
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.domain.repository.FavoriteRecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteDetailsViewModel @Inject constructor(
    private val favoriteRecipesRepository: FavoriteRecipesRepository
) : ViewModel() {

    suspend fun getRecipeByUri(uri: String): FavoriteRecipe? {
        return favoriteRecipesRepository.findRecipeById(uri)
    }
}