package com.example.foodie.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.State
import com.example.foodie.dishes.data.model.FoodRandom
import com.example.foodie.search.domain.repository.SearchFoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val searchFoodRepository: SearchFoodRepository): ViewModel() {
    private val _state = MutableStateFlow<State<FoodRandom>>(State.Loading)
    val state = _state.asStateFlow()

    init {
        getFoods()
    }

    private fun getFoods() {
        _state.value = State.Loading
        viewModelScope.launch {
            when (val result = searchFoodRepository.getSearchFood()) {
                is State.Error -> {
                    _state.value = State.Error(result.error)
                }
                is State.Success -> {
                    _state.value = State.Success(result.data)
                }
                else -> {
                    _state.value = State.Error(ApiError.REQUEST_FAILED)
                }
            }
        }
    }
}