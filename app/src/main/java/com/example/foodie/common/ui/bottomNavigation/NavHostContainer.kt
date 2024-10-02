package com.example.foodie.common.ui.bottomNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.foodie.details.ui.DetailsScreen
import com.example.foodie.dishes.ui.DishesScreen
import com.example.foodie.profile.ui.ProfileScreen
import com.example.foodie.search.ui.SearchScreen


@Composable
fun NavHostContainer(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            if (navController.currentBackStackEntryAsState().value?.destination?.route != "${Routes.details}/{uri}") {
                BottomNavigationBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.dishes,
            modifier = Modifier.let {
                if (navController.currentBackStackEntryAsState().value?.destination?.route == "${Routes.details}/{uri}")
                    it
                else
                    it.padding(innerPadding)
            }
        ) {
            composable(route = Routes.dishes) {
                DishesScreen(navController)
            }
            composable(
                route = "${Routes.details}/{uri}",
                arguments = listOf(navArgument("uri") { type = NavType.StringType })
            ) { backStackEntry ->
                val uri = backStackEntry.arguments?.getString("uri")
                uri?.let { DetailsScreen(navController, it) }
            }
            composable(route = Routes.search) {
                SearchScreen(navController = navController)
            }
            composable(route = Routes.profile) {
                ProfileScreen()
            }
        }
    }
}
