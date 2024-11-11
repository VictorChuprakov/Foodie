package com.example.foodie.favorite_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.domain.repository.FavoriteRecipesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = FavoriteDetailsViewModel.FavoriteDetailsViewModelFactory::class)
class FavoriteDetailsViewModel @AssistedInject constructor(
    @Assisted val id: String,
    private val favoriteRecipesRepository: FavoriteRecipesRepository
) : ViewModel() {

    private val _recipe = MutableStateFlow<FavoriteRecipe?>(null)
    val recipe = _recipe.asStateFlow()

    @AssistedFactory
    interface FavoriteDetailsViewModelFactory{
        fun create(id:String):FavoriteDetailsViewModel
    }

    init {
        getRecipeById()
    }

    private fun getRecipeById() {
        viewModelScope.launch(Dispatchers.IO) {
            val recipeData = favoriteRecipesRepository.findRecipeById(id)
            _recipe.value = recipeData
        }
    }
}