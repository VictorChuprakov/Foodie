package com.example.foodie.dishes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.ui.SharedViewModel


@Composable
fun DishesGridView(
    hits: List<Hit>,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 170.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(hits, key = { it.recipe.uri }) { item ->
            DishCard(item, navController, sharedViewModel)
        }
    }
}



