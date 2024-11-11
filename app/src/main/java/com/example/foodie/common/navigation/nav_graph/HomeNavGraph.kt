package com.example.foodie.common.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.foodie.common.navigation.HOME_GRAPH_ROUTE
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.bottomNavigation.MainScreen

fun NavGraphBuilder.homeNavGraph(){

    navigation(
        startDestination = Screen.Main.route,
        route = HOME_GRAPH_ROUTE
    ){
        composable(route = Screen.Main.route){
            MainScreen()
        }
    }
}