package com.example.foodie.dishes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.ui.SharedViewModel

@Composable
fun DishesListView(
    hits: List<Hit>,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(hits, key = { it.recipe.uri }) { item ->
            DishCard(item,navController,sharedViewModel)
        }
    }
}


