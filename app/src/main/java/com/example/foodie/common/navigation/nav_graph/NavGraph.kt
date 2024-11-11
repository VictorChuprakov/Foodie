package com.example.foodie.common.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.foodie.common.navigation.ROOT_GRAPH_ROUTE


@Composable
fun NavGraph(navController: NavHostController, startDestination: String,modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = ROOT_GRAPH_ROUTE,
        modifier = modifier
    ) {
        authNavGraph(navController)
        homeNavGraph()
        onBoardingNavGraph()
    }
}