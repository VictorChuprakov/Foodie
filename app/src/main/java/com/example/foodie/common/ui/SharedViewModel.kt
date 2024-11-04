package com.example.foodie.common.ui

import android.content.Context
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
import com.example.foodie.common.utils.cacheImages
import com.example.foodie.dishes.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
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
    private val searchHistoryRepository: SearchHistoryRepository,
    private val favoriteRecipesRepository: FavoriteRecipesRepository,
    private val dataPreference: DataPreference
) : ViewModel() {

    private val _state = MutableStateFlow<State<FoodResponce>>(State.Loading)
    val state: StateFlow<State<FoodResponce>> = _state.asStateFlow()

    val historySearch: StateFlow<List<SearchHistory>> = searchHistoryRepository.getAllQueries()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val favoriteRecipes: Flow<List<FavoriteRecipe
            >> = favoriteRecipesRepository.getAllRecipes()

    private val _lastQuery = MutableStateFlow("")
    private val _lastTime = MutableStateFlow(0f)
    private val _lastMealType = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)

    val lastTime = _lastTime.asStateFlow()
    val lastMealType = _lastMealType.asStateFlow()
    val isLoading = _isLoading.asStateFlow()


    init {
        Log.d("SharedViewModel", "ViewModel initialized")
        loadLastValuesAndTrackChanges()

    }


    fun load() {
        viewModelScope.launch {
            _isLoading.value = true  // Устанавливаем флаг загрузки в true
            try {
                delay(2000L)
                trackLastValuesChanges()
            } finally {
                _isLoading.value = false  // Убираем индикатор загрузки
            }
        }
    }

    private fun loadLastValuesAndTrackChanges() {
        viewModelScope.launch {
            val savedQuery = dataPreference.savedQuery.first()
            val savedMealType = dataPreference.savedMealType.first()
            val savedTime = dataPreference.savedTime.first()

            Log.d(
                "SharedViewModel",
                "Loaded last values: query = $savedQuery, mealType = $savedMealType, time = $savedTime"
            )

            // Обновляем state
            _lastQuery.value = savedQuery
            _lastMealType.value = savedMealType
            _lastTime.value = savedTime

            // Теперь отслеживаем изменения и запускаем запрос при изменении
            trackLastValuesChanges()
        }
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
            _isLoading.value = true
            val result = searchFoodRepository.searchFoodByCriteria(query, mealType, time)
            Log.d("SharedViewModel", "Fetched food result: $result")
            _state.value = result
            _isLoading.value = false

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
        viewModelScope.launch {
            Log.d("SharedViewModel", "Saving last query: $query")
            dataPreference.saveQuery(query)
            _lastQuery.value = query
        }
    }

    fun saveFilter(mealType: String, time: Float) {
        viewModelScope.launch {
            Log.d("SharedViewModel", "Saving filter with MealType: $mealType, Time: $time")

            // Параллельное выполнение сохранений без ожидания результата
            val timeJob = launch { dataPreference.saveTime(time) }
            val mealTypeJob = launch { dataPreference.saveMealType(mealType) }

            // Ожидание завершения обоих корутин
            timeJob.join()
            mealTypeJob.join()
            // Обновляем значения после завершения операций
            _lastTime.value = time
            _lastMealType.value = mealType
        }
    }


    fun saveFavoriteRecipe(recipe: FavoriteRecipe, context: Context) {
        viewModelScope.launch {

            val cachedImagePaths =
                cacheImages(context, listOf(recipe.image, recipe.images.large.url))

            // Обновляем рецепт с новыми путями к изображениям
            val updatedRecipe = recipe.copy(
                image = cachedImagePaths[0], // Обновляем основное изображение
                images = recipe.images.copy( // Обновляем вложенные изображения
                    large = recipe.images.large.copy(url = cachedImagePaths[1])
                )
            )

            // Сохраняем обновленный рецепт в базе данных
            favoriteRecipesRepository.addFavoriteRecipe(updatedRecipe)


        }
    }

    // Функция для удаления рецепта из избранного
    fun removeFavoriteRecipe(uri: String) {
        viewModelScope.launch {
            favoriteRecipesRepository.removeFavoriteRecipe(uri)
        }
    }
}
