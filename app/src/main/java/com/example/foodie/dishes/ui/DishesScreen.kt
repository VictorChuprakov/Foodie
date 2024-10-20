package com.example.foodie.dishes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.foodie.common.data.api.State
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.common.ui.SnackbarMake
import com.example.foodie.dishes.ui.components.FoodList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.example.foodie.R
import com.example.foodie.common.ui.components.loading_indicator.LoadingIndicator


@Composable
fun DishesScreen(navController: NavController,sharedViewModel: SharedViewModel) {
    val state by sharedViewModel.state.collectAsState()
    val isLoading by sharedViewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
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
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {sharedViewModel.load()},
                    indicator = {state,refresh->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = refresh,
                            backgroundColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            contentColor = colorResource(R.color.primary_green)
                        )
                    }
                ) {
                    val successState = state as State.Success
                    val hits = successState.data.hits
                    FoodList(hit = hits, navController, sharedViewModel)
                }
            }
        }
    }
}

