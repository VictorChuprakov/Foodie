package com.example.foodie.common.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.common.data.dataPreference.DataPreference
import com.example.foodie.common.navigation.AUTH_GRAPH_ROUTE
import com.example.foodie.common.navigation.HOME_GRAPH_ROUTE
import com.example.foodie.common.navigation.ON_BOARDING_GRAPH_ROTE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val repository: DataPreference
) : ViewModel() {


    private val _startDestination: MutableState<String> = mutableStateOf(ON_BOARDING_GRAPH_ROTE)
    val startDestination: State<String> = _startDestination

    init {
        Log.d("SplashViewModel", "ViewModel initialized. Starting loading...")

        viewModelScope.launch(Dispatchers.IO) {
            repository.savedStateOnBoarding.collect { completed ->
                Log.d("SplashViewModel", "Onboarding completed: $completed")

                if (completed) {
                    _startDestination.value = HOME_GRAPH_ROUTE
                    Log.d("SplashViewModel", "Navigating to AUTH_GRAPH_ROUTE")
                } else {
                    _startDestination.value = ON_BOARDING_GRAPH_ROTE
                    Log.d("SplashViewModel", "Navigating to ON_BOARDING_GRAPH_ROUTE")
                }
            }

        }
    }
}