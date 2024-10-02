package com.example.foodie.dishes.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.State
import com.example.foodie.dishes.data.model.FoodRandom
import com.example.foodie.dishes.domain.repository.GetFoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodViewModel(private val getFoodRepository: GetFoodRepository) : ViewModel() {

    private val _state = MutableStateFlow<State<FoodRandom>>(State.Loading)
    val state = _state.asStateFlow()

    init {
        getFoods()
    }

    private fun getFoods() {
        _state.value = State.Loading
        viewModelScope.launch {
            when (val result = getFoodRepository.getFood()) {
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
