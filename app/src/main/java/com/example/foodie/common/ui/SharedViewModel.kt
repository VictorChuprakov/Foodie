package com.example.foodie.common.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.api.State
import com.example.foodie.common.data.dataPreference.DataPreference
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.data.room.entities.SearchHistory
import com.example.foodie.common.domain.repository.FavoriteRecipesRepository
import com.example.foodie.common.domain.repository.SearchHistoryRepository
import com.example.foodie.dishes.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val searchFoodRepository: FoodRepository,
    private val searchHistoryRepository: SearchHistoryRepository,
    private val favoriteRecipesRepository: FavoriteRecipesRepository,
    private val dataPreference: DataPreference
) : ViewModel() {

    private val _state = MutableStateFlow<State<FoodResponce>>(State.Idle)
    val state: StateFlow<State<FoodResponce>> = _state.asStateFlow()

    val historySearch: StateFlow<List<SearchHistory>> = searchHistoryRepository.getAllQueries()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val favoriteRecipes: Flow<List<FavoriteRecipe>> = favoriteRecipesRepository.getAllRecipes()

    private val _lastQuery = MutableStateFlow("")
    private val _lastTime = MutableStateFlow(0f)
    private val _lastMealType = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)
    private var isFirstLoad = true

    val lastTime = _lastTime.asStateFlow()
    val lastMealType = _lastMealType.asStateFlow()
    val isLoading = _isLoading.asStateFlow()


    init {
        Log.d("SharedViewModel", "ViewModel initialized")
        initializeData()
    }


    fun load() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                delay(2000L)
                observeFiltersAndFetch()
            } finally {
                _isLoading.value = false
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeFiltersAndFetch() {
        Log.d("SharedViewModel", "Setting up observer for filters (lastTime, lastMealType)")

        viewModelScope.launch {
            combine(_lastQuery, _lastTime, _lastMealType) { query, time, mealType ->
                Triple(query, time, mealType) // Собираем все значения в один объект
            }
                .debounce(300)
                .distinctUntilChanged() // Этот оператор гарантирует, что коллектор будет запускаться только при изменении значений
                .collect { (query, time, mealType) ->
                    Log.d(
                        "SharedViewModel",
                        "Filters changed - lastTime: $time, lastMealType: $mealType query: $query"
                    )
                    // Вызываем метод после того как все значения обновлены
                    getFoods(query, mealType, time)
                }
        }
    }


    private fun initializeData() {
        if (isFirstLoad) {
            Log.d("SharedViewModel", "First load detected, initializing data from DataPreference")
            viewModelScope.launch(Dispatchers.IO) {
                val (query, time, mealType) = listOf(
                    async { dataPreference.savedQuery.first().toString() },
                    async { dataPreference.savedTime.first() as Float },
                    async { dataPreference.savedMealType.first().toString() }
                ).awaitAll()

                _lastQuery.value = query.toString()
                _lastTime.value = time as Float
                _lastMealType.value = mealType.toString()

                isFirstLoad = false
                Log.d("SharedViewModel", "Data initialization completed")
                observeFiltersAndFetch()
            }
        }
    }


    private fun getFoods(query: String, mealType: String, time: Float) {
        Log.d(
            "SharedViewModel",
            "Fetching foods for query: $query, mealType: $mealType, time: $time"
        )
        _state.value = State.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = searchFoodRepository.searchFoodByCriteria(query, mealType, time)
            Log.d("SharedViewModel", "Fetched food result: $result")
            _state.value = result
        }
    }

    fun saveQuery(query: String) {
        viewModelScope.launch {
            Log.d("SharedViewModel", "Saving query: $query")
            if (searchHistoryRepository.getQueryByText(query) == null) {
                Log.d("SharedViewModel", "Inserting query into database: $query")
                searchHistoryRepository.addSearchQuery(SearchHistory(query = query))
            }
            saveLastQuery(query)
        }
    }

    fun saveLastQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataPreference.saveQuery(query)
            _lastQuery.value = query
        }
    }

    fun saveFilter(mealType: String, time: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("SharedViewModel", "Saving filter with MealType: $mealType, Time: $time")

            // Параллельно сохраняем время и тип еды
            val time1 = async { dataPreference.saveTime(time) }
            val time2 = async { dataPreference.saveMealType(mealType) }

            // Ждем завершения обеих корутин
            awaitAll(time1, time2)

            // Обновляем значения после того, как обе операции завершены
            _lastTime.value = time
            _lastMealType.value = mealType

        }
    }


    // Сохраняем обновленный рецепт в базе данных

    fun saveFavoriteRecipe(recipe: FavoriteRecipe) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRecipesRepository.addFavoriteRecipe(recipe)
        }
    }

    // Функция для удаления рецепта из избранного
    fun removeFavoriteRecipe(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRecipesRepository.removeFavoriteRecipeById(id)
        }
    }
}
