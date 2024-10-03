package com.example.foodie.search.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.example.foodie.common.data.api.State
import com.example.foodie.common.ui.LoadingIndicator
import com.example.foodie.common.ui.SnackbarMake
import com.example.foodie.common.ui.components.ListFood
import com.example.foodie.search.ui.components.EmptySearchMessage
import com.example.foodie.search.ui.components.Search
import com.example.foodie.search.ui.components.SearchHistory

@Composable
fun SearchScreen(navController: NavController) {
    val searchViewModel: SearchViewModel = hiltViewModel()
    val state by searchViewModel.state.collectAsState()
    val search by searchViewModel.search.collectAsState()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Search(searchViewModel)
        SearchHistory(
            searchQuery = search,
            onSearchSelected = { query -> searchViewModel.getFoods(query) },
            onDeleteSelected = { queryId -> searchViewModel.deleteQuery(queryId) },
            onDeleteAll = { searchViewModel.deleteAll() }
        )

        if (search.isEmpty()) {
            EmptySearchMessage()
        } else {
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
                    ListFood(hit = hits, navController)
                }
            }
        }
    }
}

