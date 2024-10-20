package com.example.foodie.favorite_details.ui

import androidx.lifecycle.ViewModel
import com.example.foodie.common.data.room.FavoriteRecipes
import com.example.foodie.common.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoriteDetailsViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository // добавьте сюда ваши зависимости
) : ViewModel() {

    suspend fun getRecipeByUri(uri: String): FavoriteRecipes? {
        return databaseRepository.getRecipeByUri(uri)
    }
}