package com.example.foodie.details.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.details.domain.repository.FoodDetailsRepository
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val foodDetailsRepository: FoodDetailsRepository,
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow<State<FoodResponce>>(State.Loading)
    val state = _state.asStateFlow()


    fun getFoodsId() {
        val id: String? = savedStateHandle["foodId"]
        _state.value = State.Loading

        viewModelScope.launch {
            if (id != null) {
                val result = foodDetailsRepository.getFood(id)
                _state.value = result
            }
        }
    }
}