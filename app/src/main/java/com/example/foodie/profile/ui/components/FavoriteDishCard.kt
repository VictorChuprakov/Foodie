package com.example.foodie.profile.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.components.dishes_card_components.RecipeDetails
import com.example.foodie.common.ui.components.dishes_card_components.RecipeLabel
import com.example.foodie.dishes.ui.components.shimmerEffect


@Composable
fun FavoriteDishCard(hit: FavoriteRecipe, navController: NavController, onClick: () -> Unit) {
    val context = LocalContext.current
    var isImageLoaded by remember { mutableStateOf(true) }

    Column(modifier = Modifier.clickable {
        val encodedUri = Uri.encode(hit.uri)
        navController.navigate("${Screen.FavoriteDetails.route}/$encodedUri")
    }) {
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
                    .data(hit.images.large.url)
                    .crossfade(true)
                    .size(Size.ORIGINAL)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                onSuccess = { isImageLoaded = false },
                onError = { isImageLoaded = true }
            )
            Box(
                modifier = Modifier
                    .padding(end = 15.dp, top = 15.dp)
                    .size(35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondaryContainer
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        RecipeLabel(label = hit.label)
        Spacer(modifier = Modifier.height(10.dp))
        RecipeDetails(mealType = hit.mealType, totalTime = hit.totalTime)
    }
}


