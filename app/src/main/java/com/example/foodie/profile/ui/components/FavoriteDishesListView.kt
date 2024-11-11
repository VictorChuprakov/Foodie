package com.example.foodie.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.common.data.room.entities.FavoriteRecipe
import com.example.foodie.profile.ui.ProfileViewModel

@Composable
fun FavoriteDishesListView(
    favoriteHit: List<FavoriteRecipe>,
    navController: NavController,
    profileViewModel: ProfileViewModel,
) {
    if (favoriteHit.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.favorite_dishes_empty_message),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(favoriteHit, key = { it.uri }) { item ->
                FavoriteDishCard(
                    item,
                    navController,
                    onClick = {
                        profileViewModel.removeFavoriteRecipe(item.uri)
                    }
                )
            }
        }
    }
}
