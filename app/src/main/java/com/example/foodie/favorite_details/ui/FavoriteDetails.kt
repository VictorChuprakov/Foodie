package com.example.foodie.favorite_details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.foodie.R
import com.example.foodie.common.ui.components.details_bottom_sheet.ButtonInstructions
import com.example.foodie.common.ui.components.details_bottom_sheet.Discription
import com.example.foodie.common.ui.components.details_bottom_sheet.IngredientsList
import com.example.foodie.common.ui.components.details_bottom_sheet.NutrientsColumn
import com.example.foodie.common.ui.components.details_bottom_sheet.NutrientsRow
import com.example.foodie.common.ui.components.details_bottom_sheet.NutritionPieChart

/**
 * Экран деталей блюда, отображает информацию о рецепте.
 *
 * @param navController Контроллер навигации для управления переходами между экранами приложения.
 * @param id Уникальный идентификатор блюда, который используется для получения данных о рецепте.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteDetailsScreen(navController: NavController, id: String) {
    val favoriteDetailsViewModel: FavoriteDetailsViewModel =
        hiltViewModel<FavoriteDetailsViewModel, FavoriteDetailsViewModel.FavoriteDetailsViewModelFactory> { factory ->
            factory.create(id)
        }
    val recipe by favoriteDetailsViewModel.recipe.collectAsState()
    val context = LocalContext.current
    val scaffoldSheetState = rememberBottomSheetScaffoldState()
    val height = LocalConfiguration.current.screenHeightDp.dp

    BottomSheetScaffold(
        scaffoldState = scaffoldSheetState,
        sheetPeekHeight = height * 0.65f,
        sheetContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(horizontal = 20.dp)
            ) {
                item {
                    recipe?.let { hit ->
                        Discription(hit.label, hit.totalTime, hit.mealType)
                        IngredientsList(hit.ingredientLines)
                        NutritionPieChart(hit.calories, hit.totalNutrients)
                        NutrientsRow(hit.totalNutrients)
                        NutrientsColumn(hit.totalNutrients, hit.calories)
                        ButtonInstructions(hit.url)
                    }
                }
            }
        },
    ) {
        recipe?.let { hit ->
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data(hit.images.large.url)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
                IconButton(
                    onClick = { navController.popBackStack() },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier
                        .padding(start = 30.dp, top = 60.dp)
                        .size(60.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondaryContainer
                    )
                }
            }
        }
    }
}



