package com.example.foodie.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.foodie.R
import com.example.foodie.common.data.api.State
import com.example.foodie.common.ui.components.loading_indicator.LoadingIndicator
import com.example.foodie.common.ui.SnackbarMake
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
fun DetailsScreen(navController: NavController, id: String) {
    val foodViewModel: DetailsViewModel = hiltViewModel()
    foodViewModel.savedStateHandle["foodId"] = id
    foodViewModel.getFoodsId()
    val state by foodViewModel.state.collectAsState(initial = State.Loading)
    val context = LocalContext.current

    val scaffoldSheetState = rememberBottomSheetScaffoldState()
    val height = LocalConfiguration.current.screenHeightDp.dp

    // Основной компонент BottomSheetScaffold
    BottomSheetScaffold(
        scaffoldState = scaffoldSheetState,
        sheetPeekHeight = height * 0.65f,
        sheetContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(horizontal = 20.dp)
            ) {
                // Отображение информации о пище
                item {
                    when (state) {
                        is State.Error -> {
                            val errorState = state as State.Error
                            SnackbarMake(errorState.error.name) // Отображение сообщения об ошибке
                        }

                        is State.Loading -> {
                            LoadingIndicator() // Индикатор загрузки
                        }

                        is State.Success -> {
                            val successState = state as State.Success
                            val hits = successState.data.hits // Получаем данные о блюде
                            hits.forEach { hit ->
                                // Отображение деталей рецепта
                                Discription(
                                    hit.recipe.label,
                                    hit.recipe.totalTime,
                                    hit.recipe.mealType
                                )
                                IngredientsList(hit.recipe.ingredientLines) // Список ингредиентов
                                NutritionPieChart(
                                    hit.recipe.calories,
                                    hit.recipe.totalNutrients
                                ) //Данные о питательных веществах
                                NutrientsRow(hit.recipe.totalNutrients) // Строка с питательными веществами
                                NutrientsColumn(
                                    hit.recipe.totalNutrients,
                                    hit.recipe.calories
                                ) // Столбец с питательными веществами
                                ButtonInstructions(hit.recipe.url) // Кнопка с инструкциями по приготовлению
                            }
                        }
                    }
                }
            }
        },
    ) {
        // Контент основного экрана
        when (state) {
            is State.Error -> {
                val errorState = state as State.Error
                SnackbarMake(errorState.error.name) // Отображение сообщения об ошибке
            }

            is State.Success -> {
                val successState = state as State.Success
                val hits = successState.data.hits // Получаем данные о блюде
                hits.forEach { hit ->
                    Box {
                        // Отображение изображения блюда Coil
                        AsyncImage(
                            model = ImageRequest.Builder(context = context)
                                .data(hit.recipe.images.large.url)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth()
                        )
                        // Кнопка для возврата на предыдущий экран
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.padding(start = 30.dp, top = 60.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp) // Размер кнопки
                                    .background(colorResource(id = R.color.white20)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_arrow_back),
                                    contentDescription = null,
                                    tint = colorResource(R.color.primary_blue)
                                )
                            }
                        }
                    }
                }
            }
            is State.Loading -> {
                LoadingIndicator() // Индикатор загрузки
            }
        }
    }
}







