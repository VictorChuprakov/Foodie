package com.example.foodie.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodie.search.domain.repository.SearchFoodRepository

class SearchViewModelFactory(private val searchFoodRepository: SearchFoodRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(searchFoodRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}