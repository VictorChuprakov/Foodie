package com.example.foodie.dishes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.foodie.common.data.api.State
import com.example.foodie.common.ui.LoadingIndicator
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.common.ui.SnackbarMake
import com.example.foodie.common.ui.components.ListFood

@Composable
fun DishesScreen(navController: NavController,sharedViewModel: SharedViewModel) {
    val state by sharedViewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when (state) {
            is State.Error -> {
                val errorState = state as State.Error
                SnackbarMake(errorState.error.name)
            }
            is State.Loading -> {
                LoadingIndicator()
            }
            is State.Success -> {
                val successState = state as State.Success
                val hits = successState.data.hits
                ListFood(hit = hits,navController)
            }
        }
    }
}

