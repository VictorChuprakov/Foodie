package com.example.foodie.profile.ui.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.foodie.R
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.components.dishes_card_components.RecipeDetails
import com.example.foodie.common.ui.components.dishes_card_components.RecipeLabel
import com.example.foodie.profile.ui.ProfileViewModel
import java.io.File

/**
 * Компонент, отображающий карточку блюда в избранном.
 *
 * @param hit Данные о рецепте, полученные из API.
 * @param navController Контроллер навигации для перехода между экранами приложения.
 * @param profileViewModel ViewModel для управления состоянием избранных рецептов - удаление.
 **/
@Composable
fun FavoriteDishCard(hit: FavoriteRecipe, navController: NavController, profileViewModel: ProfileViewModel) {
    val context = LocalContext.current
    Column(modifier = Modifier.clickable {
        // Переходим на экран деталей блюда по клику на карточку.
        val encodedUri = Uri.encode(hit.uri)
        navController.navigate("${Screen.FavoriteDetails.route}/$encodedUri")
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
            // Загружаем изображение блюда с помощью Coil.

            AsyncImage(
                model = ImageRequest.Builder(context = context)
                    .data(File(hit.image))
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
                // Кнопка для /удаления рецепта из избранного.
                IconButton(onClick = {
                        profileViewModel.removeFavoriteRecipe(hit.uri)

                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = colorResource(R.color.primary_blue)
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        // Отображаем название рецепта.
        RecipeLabel(label = hit.label)
        Spacer(modifier = Modifier.height(10.dp))
        // Отображаем детали рецепта.
        RecipeDetails(mealType = hit.mealType, totalTime = hit.totalTime)
    }
}


