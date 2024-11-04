package com.example.foodie.favorite_details.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.domain.repository.FavoriteRecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteDetailsViewModel @Inject constructor(
    private val favoriteRecipesRepository: FavoriteRecipesRepository,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Состояние для хранения выбранного рецепта
    private val _recipe = mutableStateOf<FavoriteRecipe?>(null)
    val recipe = _recipe

    fun getRecipeById() {
        viewModelScope.launch {
            val id: String? = savedStateHandle["favoriteFoodId"]
            if (id != null) {
            _recipe.value = favoriteRecipesRepository.findRecipeById(id)
            }
        }
    }
}