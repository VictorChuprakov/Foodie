package com.example.foodie.common.data.dataPreference

import android.content.Context
import android.health.connect.datatypes.MealType
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class DataPreference(private val context: Context) {

    // Ключи для настроек
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preference_name")
        val QUERY_KEY = stringPreferencesKey("query")
        val MEAL_TYPE_KEY = stringPreferencesKey("meal_type")
        val TIME_KEY = floatPreferencesKey("time")

    }

    // Сохранение запроса
    suspend fun saveQuery(query: String) {
        context.dataStore.edit { preferences ->
            preferences[QUERY_KEY] = query
        }
    }

    suspend fun saveMealType(mealType: String) {
        context.dataStore.edit { preferences ->
            preferences[MEAL_TYPE_KEY] = mealType
        }
    }

    suspend fun saveTime(time: Float) {
        context.dataStore.edit { preferences ->
            preferences[TIME_KEY] = time
        }
    }

    val savedQuery: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[QUERY_KEY] ?: "" }

    val savedMealType: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[MEAL_TYPE_KEY] ?: "null" }


    val savedTime: Flow<Float> = context.dataStore.data
        .map { preferences ->
            preferences[TIME_KEY] ?: 10f
        }
}

