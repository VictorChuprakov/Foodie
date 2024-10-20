package com.example.foodie.dishes.ui.components

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.foodie.R
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.data.room.FavoriteRecipes
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.common.ui.components.dishes_card_components.RecipeDetails
import com.example.foodie.common.ui.components.dishes_card_components.RecipeLabel

/**
* Компонент, отображающий карточку блюда с возможностью добавить его в избранное.
*
* @param hit Данные о рецепте, полученные из API.
* @param navController Контроллер навигации для перехода между экранами приложения.
* @param sharedViewModel ViewModel для управления состоянием избранных рецептов и их хранения.
**/
@SuppressLint("UnrememberedMutableState")
@Composable
fun FoodDishCard(hit: Hit, navController: NavController, sharedViewModel: SharedViewModel) {

    // Получаем текущий контекст для использования в библиотеке Coil.
    val context = LocalContext.current

    // Подписываемся на состояние избранных рецептов из ViewModel.
    val favoriteRecipesState = sharedViewModel.favoriteRecipes.collectAsState(initial = emptyList())
    val favoriteRecipes = favoriteRecipesState.value
    val isFavorite = favoriteRecipes.any { it.uri == hit.recipe.uri }
    val newFavoriteState = remember { mutableStateOf(isFavorite) }
    LaunchedEffect(favoriteRecipesState.value) {
        newFavoriteState.value = favoriteRecipes.any { it.uri == hit.recipe.uri }
    }

    Column(modifier = Modifier.clickable {
        val encodedUri = Uri.encode(hit.recipe.uri)
        navController.navigate("${Screen.Detail.route}/$encodedUri")
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
            // Загружаем изображение блюда с помощью Coil.
            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data(hit.recipe.image)
                    .crossfade(true)
                    .transformations(RoundedCornersTransformation(50f))
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )
            Box(
                modifier = Modifier
                    .padding(end = 15.dp, top = 15.dp)
                    .size(35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.6f),
                                Color.White.copy(alpha = 0.4f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Кнопка для добавления/удаления рецепта из избранного.
                IconButton(onClick = {
                    if (newFavoriteState.value) {
                        // Удаляем рецепт из избранного, если он уже есть.
                        sharedViewModel.removeFavoriteRecipe(hit.recipe.uri)
                    } else {
                        // Сохраняем рецепт в избранное, если его там нет.
                        val favoriteRecipe = FavoriteRecipes(
                            calories = hit.recipe.calories,
                            image = hit.recipe.image,
                            images = hit.recipe.images,
                            label = hit.recipe.label,
                            mealType = hit.recipe.mealType,
                            totalTime = hit.recipe.totalTime,
                            ingredientLines = hit.recipe.ingredientLines,
                            totalNutrients = hit.recipe.totalNutrients,
                            totalWeight = hit.recipe.totalWeight,
                            url = hit.recipe.url,
                            uri = hit.recipe.uri
                        )
                        sharedViewModel.saveFavoriteRecipe(favoriteRecipe, context)
                    }
                    // Меняем состояние избранного.
                    newFavoriteState.value = !newFavoriteState.value
                }) {
                    // Отображаем иконку в зависимости от состояния "избранное".
                    Icon(
                        painter = if (newFavoriteState.value) {
                            painterResource(R.drawable.ic_favorite)
                        } else {
                            painterResource(R.drawable.ic_favorite_borber)
                        },
                        contentDescription = null,
                        tint = colorResource(R.color.primary_blue)
                    )

                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        // Отображаем название рецепта.
        RecipeLabel(label = hit.recipe.label)
        Spacer(modifier = Modifier.height(10.dp))
        // Отображаем детали рецепта.
        RecipeDetails(mealType = hit.recipe.mealType, totalTime = hit.recipe.totalTime)
    }
}


