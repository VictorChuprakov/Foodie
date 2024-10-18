package com.example.foodie.common.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.foodie.R
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.data.model.Recipe
import com.example.foodie.common.ui.bottomNavigation.Routes

@Composable
fun DishCard(hit: Hit, navController: NavController) {
    val context = LocalContext.current
    Column(modifier = Modifier.clickable {
        val encodedUri = Uri.encode(hit.recipe.uri)
        navController.navigate("${Routes.details}/$encodedUri")
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
                    .size(30.dp) // Увеличил размер для лучшей видимости
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.6f), // Полупрозрачный белый
                                Color.White.copy(alpha = 0.4f)  // Еще более прозрачный
                            )
                        )
                    )
                    .blur(10.dp), // Уменьшил размытие для большего эффекта
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = colorResource(R.color.white)
                )
            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        RecipeLabel(label = hit.recipe.label)
        Spacer(modifier = Modifier.height(10.dp))
        RecipeDetails(recipe = hit.recipe)
    }
}


@Composable
fun RecipeLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
        color = colorResource(id = R.color.primary_blue),
        modifier = Modifier.width(150.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun RecipeDetails(recipe: Recipe) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = recipe.mealType.joinToString(", "),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.primary_gray),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(5.dp))
        CircleDot()
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = if (recipe.totalTime == 0.0) "> ∞ mins" else "> ${recipe.totalTime.toInt()} mins",
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.primary_gray),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CircleDot() {
    Box(
        modifier = Modifier
            .size(5.dp)
            .clip(CircleShape)
            .background(colorResource(id = R.color.primary_gray))
    )
}
