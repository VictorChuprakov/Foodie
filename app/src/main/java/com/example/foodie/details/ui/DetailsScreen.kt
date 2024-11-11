package com.example.foodie.details.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.example.foodie.common.data.api.ApiError
import com.example.foodie.common.data.api.State
import com.example.foodie.common.ui.DisplayErrorScreen
import com.example.foodie.common.ui.components.details_bottom_sheet.ButtonInstructions
import com.example.foodie.common.ui.components.details_bottom_sheet.Discription
import com.example.foodie.common.ui.components.details_bottom_sheet.IngredientsList
import com.example.foodie.common.ui.components.details_bottom_sheet.NutrientsColumn
import com.example.foodie.common.ui.components.details_bottom_sheet.NutrientsRow
import com.example.foodie.common.ui.components.details_bottom_sheet.NutritionPieChart
import com.example.foodie.common.ui.components.loading_indicator.LoadingIndicator

/**
 * Экран деталей блюда, отображает информацию о рецепте.
 *
 * @param navController Контроллер навигации для управления переходами между экранами приложения.
 * @param id Уникальный идентификатор блюда, который используется для получения данных о рецепте.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, id: String) {
    val detailViewModel: DetailViewModel =
        hiltViewModel<DetailViewModel, DetailViewModel.DetailViewModelFactory> { factory ->
            factory.create(id)
        }

    val state by detailViewModel.state.collectAsState()
    val context = LocalContext.current
    val scaffoldSheetState = rememberBottomSheetScaffoldState()
    val height = LocalConfiguration.current.screenHeightDp.dp

    when (state) {
        is State.Error -> {
            val errorState = state as State.Error
            val errorMessage = when (errorState.error) {
                ApiError.NETWORK_ERROR -> R.string.network_error_message
                ApiError.RESPONSE_NULL -> R.string.response_null_message
                ApiError.REQUEST_FAILED -> R.string.request_failed_message
                ApiError.UNEXPECTED_ERROR -> R.string.unexpected_error_message
            }
            DisplayErrorScreen(
                errorMessage,
                onClick = { detailViewModel.getFoodsId() }
            )
        }

        is State.Loading -> {
            LoadingIndicator()
        }

        is State.Success -> {
            val successState = state as State.Success
            val hits = successState.data.hits
            BottomSheetScaffold(
                scaffoldState = scaffoldSheetState,
                sheetPeekHeight = height * 0.5f,
                sheetContent = {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .navigationBarsPadding()
                            .padding(horizontal = 20.dp)
                    ) {
                        item {
                            hits.forEach { hit ->
                                // Отображение деталей рецепта
                                Discription(
                                    hit.recipe.label,
                                    hit.recipe.totalTime,
                                    hit.recipe.mealType
                                )
                                IngredientsList(hit.recipe.ingredientLines)
                                NutritionPieChart(
                                    hit.recipe.calories,
                                    hit.recipe.totalNutrients
                                )
                                NutrientsRow(hit.recipe.totalNutrients)
                                NutrientsColumn(
                                    hit.recipe.totalNutrients,
                                    hit.recipe.calories
                                )
                                ButtonInstructions(hit.recipe.url)
                            }
                        }
                    }
                },
                content = {
                    hits.forEach { hit ->
                        Box {
                            AsyncImage(
                                model = ImageRequest.Builder(context = context)
                                    .data(hit.recipe.images.large.url)
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
            )
        }

        else -> {}
    }
}








