package com.example.foodie.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.State
import com.example.foodie.details.data.model.FoodDetails
import com.example.foodie.details.domain.GetFoodById
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val getFoodById: GetFoodById): ViewModel() {

    private val _state = MutableStateFlow<State<FoodDetails>>(State.Loading)
    val state = _state.asStateFlow()


    fun getFoodsId(uri: String) {
        _state.value = State.Loading
        viewModelScope.launch {
            when (val result = getFoodById.GetFoodId(uri)) {
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