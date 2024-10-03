package com.example.foodie.search.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.common.data.room.SearchQuery
import com.example.foodie.common.domain.repository.DatabaseRepository
import com.example.foodie.search.domain.repository.SearchFoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchFoodRepository: SearchFoodRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<State<FoodResponce>>(State.Loading)
    val state = _state.asStateFlow()

    val search: StateFlow<List<SearchQuery>> = databaseRepository.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    // Конструктор ViewModel
    init {
        // Вызываем функцию для получения последнего запроса
        viewModelScope.launch {
            search.collect { queries ->
                // Получаем последний запрос, если он есть
                queries.lastOrNull()?.let { lastQuery ->
                    getFoods(lastQuery.query) // Выполняем поиск
                }
            }
        }
    }

    fun getFoods(query: String) {
        _state.value = State.Loading
        viewModelScope.launch {
            when (val result = searchFoodRepository.searchFood(query)) {
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

    fun saveQuery(query: String) {
        viewModelScope.launch {
            val searchQuery = SearchQuery(query = query) // Используем только значение запроса
            databaseRepository.insert(searchQuery)
        }
    }

    fun deleteQuery(queryId: Long) {
        viewModelScope.launch {
            databaseRepository.deleteById(queryId) // Вызываем метод удаления в репозитории
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            databaseRepository.deleteAll()
        }
    }
}
