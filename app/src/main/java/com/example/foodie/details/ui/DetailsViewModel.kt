package com.example.foodie.details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.details.domain.repository.FoodDetailsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailViewModel.DetailViewModelFactory::class)
class DetailViewModel @AssistedInject constructor(
    @Assisted val id: String,
    private val foodDetailsRepository: FoodDetailsRepository,
) : ViewModel() {

    @AssistedFactory
    interface DetailViewModelFactory {
        fun create(id: String): DetailViewModel
    }

    private val _state = MutableStateFlow<State<FoodResponce>>(State.Loading)
    val state = _state.asStateFlow()


    init {
        getFoodsId()
    }

    fun getFoodsId() {
        _state.value = State.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = foodDetailsRepository.getFood(id)
        }
    }
}