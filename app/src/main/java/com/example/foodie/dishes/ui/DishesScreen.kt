package com.example.foodie.dishes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.State
import com.example.foodie.common.ui.DisplayErrorScreen
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.common.ui.components.loading_indicator.LoadingIndicator
import com.example.foodie.dishes.ui.components.DishesTabLayout
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun DishesScreen(navController: NavController, sharedViewModel: SharedViewModel) {
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
                val errorMessage = when (errorState.error) {
                    ApiError.NETWORK_ERROR -> R.string.network_error_message
                    ApiError.RESPONSE_NULL -> R.string.response_null_message
                    ApiError.REQUEST_FAILED -> R.string.request_failed_message
                    ApiError.UNEXPECTED_ERROR -> R.string.unexpected_error_message
                }
                DisplayErrorScreen(
                    errorMessage,
                    onClick = { sharedViewModel.load() }
                )
            }

            is State.Loading -> {
                LoadingIndicator()
            }

            is State.Success -> {
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = { sharedViewModel.load() },
                    indicator = { state, refresh ->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = refresh,
                            backgroundColor = MaterialTheme.colorScheme.onSurface,
                            contentColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                ) {
                    val successState = state as State.Success
                    val hits = successState.data.hits
                    DishesTabLayout(hits, navController, sharedViewModel)
                }
            }
            else -> {}
        }
    }
}

