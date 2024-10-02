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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodie.R
import com.example.foodie.common.ui.bottomNavigation.Routes
import com.example.foodie.dishes.data.model.Hit
import com.example.foodie.dishes.data.model.Recipe

@Composable
fun DishCard(hit: Hit, navController: NavController) {
    Column(modifier = Modifier.clickable {
        val encodedUri = Uri.encode(hit.recipe.uri)
        navController.navigate("${Routes.details}/$encodedUri")
    }) {
        AsyncImage(
            model = hit.recipe.images.large.url,
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)),

        )
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
    val mealTypes = recipe.mealType
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = mealTypes.joinToString(", "),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.primary_gray)
        )
        Spacer(modifier = Modifier.width(5.dp))
        CircleDot()
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = if (recipe.totalTime == 0.0) "> ∞ mins"
            else "> ${recipe.totalTime.toInt()} mins",
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.primary_gray)
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



