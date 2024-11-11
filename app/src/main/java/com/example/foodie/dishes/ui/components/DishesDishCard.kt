package com.example.foodie.dishes.ui.components

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.foodie.R
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.common.ui.components.dishes_card_components.RecipeDetails
import com.example.foodie.common.ui.components.dishes_card_components.RecipeLabel
import com.example.foodie.profile.ui.components.convertRecipeToFavoriteRecipe

@SuppressLint("UnrememberedMutableState")
@Composable
fun DishCard(hit: Hit, navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val favoriteRecipes by sharedViewModel.favoriteRecipes.collectAsState(initial = emptyList())
    val isFavorite = favoriteRecipes.any { it.uri == hit.recipe.uri }
    var isImageLoaded by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .clickable(onClick = {
                val id = Uri.encode(hit.recipe.uri)
                navController.navigate(Screen.Detail.route + "/$id")
            })
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .aspectRatio(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            if (isImageLoaded) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                )
            }

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(hit.recipe.images.large.url)
                    .crossfade(true)
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                onSuccess = { isImageLoaded = false },
                onError = { isImageLoaded = true }
            )

            FavoriteIcon(
                isFavorite = isFavorite,
                onFavoriteToggle = {
                    if (isFavorite) {
                        sharedViewModel.removeFavoriteRecipe(hit.recipe.uri)
                    } else {
                        sharedViewModel.saveFavoriteRecipe(convertRecipeToFavoriteRecipe(hit.recipe))
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        RecipeLabel(label = hit.recipe.label)
        Spacer(modifier = Modifier.height(10.dp))
        RecipeDetails(mealType = hit.recipe.mealType, totalTime = hit.recipe.totalTime)
    }
}


@Composable
private fun FavoriteIcon(isFavorite: Boolean, onFavoriteToggle: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(15.dp)
            .size(35.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onFavoriteToggle) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    if (isFavorite) {
                        R.drawable.ic_favorite
                    } else {
                        R.drawable.ic_favorite_borber
                    }
                ),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondaryContainer
            )
        }
    }
}


fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}

