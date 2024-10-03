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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodie.R
import com.example.foodie.common.data.api.State
import com.example.foodie.common.ui.LoadingIndicator
import com.example.foodie.common.ui.SnackbarMake
import com.example.foodie.details.ui.components.sheetContent.ButtonInstructions
import com.example.foodie.details.ui.components.sheetContent.Discription
import com.example.foodie.details.ui.components.sheetContent.IngredientsSection
import com.example.foodie.details.ui.components.sheetContent.NutrientsColumn
import com.example.foodie.details.ui.components.sheetContent.NutrientsRow
import com.example.foodie.details.ui.components.sheetContent.NutritionPieChart


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, uri: String) {
    val foodViewModel: DetailsViewModel = hiltViewModel()
    val state by foodViewModel.state.collectAsState(initial = State.Loading)
    LaunchedEffect(uri) {
        foodViewModel.getFoodsId(uri)

    }


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
                            SnackbarMake(errorState.error.name)
                        }

                        is State.Loading -> {
                            LoadingIndicator()
                        }

                        is State.Success -> {
                            val successState = state as State.Success
                            val hits = successState.data.hits
                            hits.forEach { hit ->
                                Discription(hit)
                                IngredientsSection(hit)
                                NutritionPieChart(hit)
                                NutrientsRow(hit)
                                NutrientsColumn(hit)
                                ButtonInstructions(hit)
                            }
                        }

                    }
                }
            }
        },
    ) {
        when (state) {
            is State.Error -> {
                val errorState = state as State.Error
                SnackbarMake(errorState.error.name)
            }
            is State.Success -> {
                val successState = state as State.Success
                val hits = successState.data.hits
                hits.forEach { hit ->
                    Box {
                        AsyncImage(
                            model = hit.recipe.images.large.url,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.padding(start = 30.dp, top = 60.dp)
                        ) {
                            Box(modifier = Modifier
                                .size(50.dp)
                                .background(colorResource(id = R.color.white20)),
                                contentAlignment = Alignment.Center) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_arrow_back),
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
            is State.Loading -> {
                LoadingIndicator()
            }
        }
    }
}

@Composable
fun Divider() {
    HorizontalDivider(
        thickness = 1.5.dp,
        color = colorResource(id = R.color.primary_gray),
    )
}





