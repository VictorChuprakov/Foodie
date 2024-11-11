package com.example.foodie.common.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodie.authorization.onboarding.ui.OnBoardingScreen
import com.example.foodie.common.navigation.ON_BOARDING_GRAPH_ROTE
import com.example.foodie.common.navigation.Screen

fun NavGraphBuilder.onBoardingNavGraph(
){
    navigation(
        startDestination = Screen.OnBoarding.route,
        route = ON_BOARDING_GRAPH_ROTE
    ) {
        composable(route = Screen.OnBoarding.route) {
            OnBoardingScreen()
        }
    }
}