package com.example.foodie.random.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.random.domain.repository.RandomFoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val randomFoodRepository: RandomFoodRepository) : ViewModel() {

    private val _state = MutableStateFlow<State<FoodResponce>>(State.Loading)
    val state = _state.asStateFlow()

    init {
        getFoods()
    }

    private fun getFoods() {
        _state.value = State.Loading
        viewModelScope.launch {
            when (val result = randomFoodRepository.getRandomFood()) {
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
