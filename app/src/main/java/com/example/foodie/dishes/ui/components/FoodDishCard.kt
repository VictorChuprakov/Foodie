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
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.common.ui.components.dishes_card_components.RecipeDetails
import com.example.foodie.common.ui.components.dishes_card_components.RecipeLabel
import com.example.foodie.profile.ui.components.convertRecipeToFavoriteRecipe

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
    val context = LocalContext.current

    val favoriteRecipesState = sharedViewModel.favoriteRecipes.collectAsState(initial = emptyList())
    val favoriteRecipes = favoriteRecipesState.value
    val isFavorite = remember(favoriteRecipes) { favoriteRecipes.any { it.uri == hit.recipe.uri } }

    Column(modifier = Modifier.clickable {
        val encodedUri = Uri.encode(hit.recipe.uri)
        navController.navigate("${Screen.Detail.route}/$encodedUri")
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
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
                IconButton(onClick = {
                    if (isFavorite) {
                        sharedViewModel.removeFavoriteRecipe(hit.recipe.uri)
                    } else {
                        val favoriteRecipe = convertRecipeToFavoriteRecipe(hit.recipe)
                        sharedViewModel.saveFavoriteRecipe(favoriteRecipe, context)
                    }
                }) {
                    Icon(
                        painter = if (isFavorite) {
                            painterResource(R.drawable.ic_favorite)
                        } else {
                            painterResource(R.drawable.ic_favorite_borber)
                        },
                        contentDescription = null,
                        tint = colorResource(R.color.white)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        RecipeLabel(label = hit.recipe.label)
        Spacer(modifier = Modifier.height(10.dp))
        RecipeDetails(mealType = hit.recipe.mealType, totalTime = hit.recipe.totalTime)
    }
}

