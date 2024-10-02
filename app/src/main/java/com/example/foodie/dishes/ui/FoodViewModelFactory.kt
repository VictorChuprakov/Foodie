package com.example.foodie.dishes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.dishes.domain.repository.GetFoodRepository

class FoodViewModelFactory(private val getFoodRepository: GetFoodRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FoodViewModel(getFoodRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
