package com.example.foodie.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.foodie.common.data.api.RetrofitModule
import com.example.foodie.common.data.api.State
import com.example.foodie.common.ui.LoadingIndicator
import com.example.foodie.common.ui.SnackbarMake
import com.example.foodie.common.ui.components.ListFood
import com.example.foodie.search.ui.components.Search

@Composable
fun SearchScreen(navController: NavController){

    val viewModelFactory = remember { SearchViewModelFactory(RetrofitModule.provideSearchFood()) }
    val searchViewModel: SearchViewModel = viewModel(factory = viewModelFactory)
    val state by searchViewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize(),
    ) {
        Search()
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