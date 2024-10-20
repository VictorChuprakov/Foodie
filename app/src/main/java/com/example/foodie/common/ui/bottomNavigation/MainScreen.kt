package com.example.foodie.common.ui.bottomNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.details.ui.DetailsScreen
import com.example.foodie.profile.ui.ProfileScreen
import com.example.foodie.dishes.ui.DishesScreen
import com.example.foodie.favorite_details.ui.FavoriteDetailsScreen
import com.example.foodie.search_history.ui.SearchScreen
import com.example.foodie.search.ui.Search


@Composable
fun MainScreen(navController: NavHostController) {
    val currentBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = currentBackStackEntry?.destination?.route
    val sharedViewModel: SharedViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            if (currentRoute != "${Screen.Detail.route}/{id}" && currentRoute != Screen.Profile.route && currentRoute != "${Screen.FavoriteDetails.route}/{id}") {
                Search(navController, sharedViewModel)
            }
        },
        bottomBar = {
            if (currentRoute != "${Screen.Detail.route}/{id}" && currentRoute != "${Screen.FavoriteDetails.route}/{id}") {
                BottomNavigationBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.let {
                if (currentRoute == "${Screen.Detail.route}/{id}" || currentRoute == "${Screen.FavoriteDetails.route}/{id}") {
                    it
                }
                else
                    it.padding(innerPadding)
            }
        ) {
            composable(route = Screen.Home.route) {
                DishesScreen(navController, sharedViewModel)
            }
            composable(
                route = "${Screen.Detail.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                val uri = backStackEntry.arguments?.getString("id")
                uri?.let { DetailsScreen(navController, it) }
            }
            composable(route = Screen.Search.route) {
                SearchScreen(navController, sharedViewModel)
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen(navController)
            }
            composable(
                route = "${Screen.FavoriteDetails.route}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                val uri = backStackEntry.arguments?.getString("id")
                uri?.let { FavoriteDetailsScreen(navController, it) }
            }
        }
    }
}
