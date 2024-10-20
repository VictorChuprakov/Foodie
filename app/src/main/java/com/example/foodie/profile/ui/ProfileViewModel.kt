package com.example.foodie.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.room.FavoriteRecipes
import com.example.foodie.common.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) :
    ViewModel() {

    val favoriteRecipes: Flow<List<FavoriteRecipes>> = databaseRepository.getAllFavoriteRecipes()


    // Функция для удаления рецепта из избранного
    fun removeFavoriteRecipe(uri: String) {
        viewModelScope.launch {
            databaseRepository.deleteFavoriteRecipe(uri)
        }
    }

}