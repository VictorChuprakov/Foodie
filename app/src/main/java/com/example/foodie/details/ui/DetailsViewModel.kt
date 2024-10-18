package com.example.foodie.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.details.domain.repository.FoodDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val foodDetailsRepository: FoodDetailsRepository) :
    ViewModel() {

    private val _state = MutableStateFlow<State<FoodResponce>>(State.Loading)
    val state = _state.asStateFlow()

    fun getFoodsId(id: String) {
        _state.value = State.Loading
        viewModelScope.launch {
            val result = foodDetailsRepository.getFood(id)
            _state.value = result
        }
    }
}