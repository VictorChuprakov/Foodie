package com.example.foodie.dishes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.ui.SharedViewModel

/**
 * Отображает список блюд в виде сетки.
 *
 * @param hit Список объектов Hit, представляющих блюда. Каждый объект содержит данные о рецепте, такие как изображение, название и другие детали.
 * @param navController Контроллер навигации для управления переходами между экранами приложения.
 * @param sharedViewModel ViewModel для управления состоянием и данными о избранных рецептах, чтобы сохранять и извлекать информацию о том, какие рецепты добавлены в избранное.
 */
@Composable
fun FoodList(hit: List<Hit>, navController: NavController, sharedViewModel: SharedViewModel) {
    // Обертка для размещения содержимого по центру
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
            // Для каждого элемента списка hit создаем карточку блюда
            items(hit) { hit ->
                FoodDishCard(hit, navController, sharedViewModel) // Вызываем компонент FoodDishCard с параметрами
            }
        }
    }
}