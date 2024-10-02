package com.example.foodie.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.details.domain.GetFoodById

class DetailsViewModelFactory(private val getFoodById: GetFoodById): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(getFoodById) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}