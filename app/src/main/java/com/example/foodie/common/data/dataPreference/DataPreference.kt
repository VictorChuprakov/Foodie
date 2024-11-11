package com.example.foodie.common.data.dataPreference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preference_name")

class DataPreference(private val context: Context) {


    // Сохранение запроса
    suspend fun saveQuery(query: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.QUERY_KEY] = query
        }
    }

    suspend fun saveMealType(mealType: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.MEAL_TYPE_KEY] = mealType
        }
    }

    suspend fun saveTime(time: Float) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TIME_KEY] = time
        }
    }

    suspend fun saveStateOnBoarding(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.ON_BOARDING] = completed
        }
    }

    val savedStateOnBoarding: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[PreferencesKeys.ON_BOARDING] ?: false }


    val savedQuery: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[PreferencesKeys.QUERY_KEY] ?: "" }

    val savedMealType: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[PreferencesKeys.MEAL_TYPE_KEY] ?: "null" }

    val savedTime: Flow<Float> = context.dataStore.data
        .map { preferences -> preferences[PreferencesKeys.TIME_KEY] ?: 10f }
}


private object PreferencesKeys {
    val QUERY_KEY = stringPreferencesKey("query")
    val MEAL_TYPE_KEY = stringPreferencesKey("meal_type")
    val TIME_KEY = floatPreferencesKey("time")
    val ON_BOARDING = booleanPreferencesKey("on_boarding")
}

