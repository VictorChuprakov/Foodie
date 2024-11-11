package com.example.foodie.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.domain.repository.FavoriteRecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val favoriteRecipesRepository: FavoriteRecipesRepository,
) : ViewModel() {

    val favoriteRecipes: Flow<List<FavoriteRecipe>> = favoriteRecipesRepository.getAllRecipes()



    fun removeFavoriteRecipe(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRecipesRepository.removeFavoriteRecipeById(uri)
        }
    }
}