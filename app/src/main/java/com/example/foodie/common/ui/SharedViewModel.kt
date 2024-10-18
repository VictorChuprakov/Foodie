package com.example.foodie.common.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.dataPreference.DataPreference
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.common.data.room.SearchQuery
import com.example.foodie.common.domain.repository.DatabaseRepository
import com.example.foodie.dishes.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val searchFoodRepository: FoodRepository,
    private val databaseRepository: DatabaseRepository,
    private val dataPreference: DataPreference
) : ViewModel() {

    private val _state = MutableStateFlow<State<FoodResponce>>(State.Loading)
    val state: StateFlow<State<FoodResponce>> = _state.asStateFlow()

    val historySearch: StateFlow<List<SearchQuery>> = databaseRepository.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _lastQuery = MutableStateFlow("")
    private val _lastTime = MutableStateFlow(0f)
    private val _lastMealType = MutableStateFlow("")

    val lastTime: StateFlow<Float> = _lastTime.asStateFlow()
    val lastMealType: StateFlow<String> = _lastMealType.asStateFlow()

    init {
        Log.d("SharedViewModel", "ViewModel initialized")
        viewModelScope.launch{
            loadLastValuesAndTrackChanges()
        }
    }

    private suspend fun loadLastValuesAndTrackChanges() {
        // Получаем сохранённые данные перед выполнением запроса
        val savedQuery = dataPreference.savedQuery.first()
        val savedMealType = dataPreference.savedMealType.first()
        val savedTime = dataPreference.savedTime.first()

        Log.d("SharedViewModel", "Loaded last values: query = $savedQuery, mealType = $savedMealType, time = $savedTime")

        // Обновляем state
        _lastQuery.value = savedQuery
        _lastMealType.value = savedMealType
        _lastTime.value = savedTime

        // Теперь отслеживаем изменения и запускаем запрос при изменении
        trackLastValuesChanges()
    }


    private fun trackLastValuesChanges() {
        viewModelScope.launch {
            Log.d("SharedViewModel", "Tracking changes in last values")
            combine(
                _lastQuery,
                _lastMealType,
                _lastTime
            ) { query, mealType, time -> Triple(query, mealType, time) }
                .collect { (query, mealType, time) ->
                    getFoods(query, mealType, time)
                }
        }
    }

    fun getFoods(query: String, mealType: String, time: Float) {
        Log.d(
            "SharedViewModel",
            "Fetching foods for query: $query, mealType: $mealType, time: $time"
        )
        _state.value = State.Loading
        viewModelScope.launch {
            val result = searchFoodRepository.searchFoodByCriteria(query, mealType, time)
            Log.d("SharedViewModel", "Fetched food result: $result")
            _state.value = result
        }
    }

    fun saveQuery(query: String) {
        viewModelScope.launch {
            Log.d("SharedViewModel", "Saving query: $query")
            if (databaseRepository.findQuery(query) == null) {
                Log.d("SharedViewModel", "Inserting query into database: $query")
                databaseRepository.insert(SearchQuery(query = query))
            }
            saveLastQuery(query)
        }
    }

    fun saveLastQuery(query: String) {
        viewModelScope.launch {
            Log.d("SharedViewModel", "Saving last query: $query")
            dataPreference.saveQuery(query)
            _lastQuery.value = query
        }
    }

    fun saveFilter(mealType: String, time: Float) {
        viewModelScope.launch {
            Log.d("SharedViewModel", "Saving filter with MealType: $mealType, Time: $time")

            // Используем async для параллельного выполнения сохранений
            val saveTimeDeferred = async { dataPreference.saveTime(time) }
            val saveMealTypeDeferred = async { dataPreference.saveMealType(mealType) }

            // Ожидаем завершения выполнения обеих операций
            saveTimeDeferred.await()
            saveMealTypeDeferred.await()

            // Обновляем значения после завершения операций
            _lastTime.value = time
            _lastMealType.value = mealType
        }
    }

}
