package com.example.foodie.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.profile.ui.ProfileViewModel
/**
 * Отображает список блюд в виде сетки.
 *
 * @param navController Контроллер навигации для управления переходами между экранами приложения.
 * @param profileViewModel ViewModel для управления состоянием и данными о избранных рецептах, чтобы удялать рецепты из избранного.
 */
@Composable
fun FavoriteFoodList(navController: NavController, profileViewModel: ProfileViewModel) {
    val hits = profileViewModel.favoriteRecipes.collectAsState(initial = emptyList())
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // LazyVerticalGrid позволяет отображать элементы в виде сетки с вертикальной прокруткой
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            // Для каждого элемента списка hit создаем карточку блюда если оно есть в озбранном
            items(hits.value) { hit ->
                FavoriteDishCard(hit, navController,profileViewModel)
            }
        }
    }
}