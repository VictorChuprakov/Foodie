package com.example.foodie.common.ui.bottomNavigation

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodie.R
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.details.ui.DetailsScreen
import com.example.foodie.dishes.ui.DishesScreen
import com.example.foodie.favorite_details.ui.FavoriteDetailsScreen
import com.example.foodie.profile.ui.ProfileScreen
import com.example.foodie.search.ui.Search
import com.example.foodie.search_history.ui.SearchScreen


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val sharedViewModel: SharedViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            if (currentRoute != "${Screen.Detail.route}/{id}" && currentRoute != Screen.Profile.route && currentRoute != "${Screen.FavoriteDetails.route}/{id}") {
                Search(navController, sharedViewModel)
            }
        },
        bottomBar = {
            if (currentRoute != "${Screen.Detail.route}/{id}" && currentRoute != "${Screen.FavoriteDetails.route}/{id}"  && currentRoute != Screen.Search.route) {
                BottomNavigationBar(navController)
            }
        },
        floatingActionButton = {
            if (currentRoute != "${Screen.Detail.route}/{id}" && currentRoute != "${Screen.FavoriteDetails.route}/{id}"  && currentRoute != Screen.Search.route) {
                FloatingActionButton(
                    onClick = { /* Действие сканирования */ },
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(70.dp)
                        .offset(y = 55.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_scan),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.let {
                    if (currentRoute == "${Screen.Detail.route}/{id}" || currentRoute == "${Screen.FavoriteDetails.route}/{id}") {
                        it
                    } else
                        it.padding(innerPadding)
                }
            ) {
                composable(route = Screen.Home.route) {
                    DishesScreen(navController, sharedViewModel)
                }
                composable(
                    route = "${Screen.Detail.route}/{id}",
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")
                    id?.let { DetailsScreen(navController,it) }
                }
                composable(route = Screen.Search.route) {
                    SearchScreen(navController, sharedViewModel)
                }
                composable(route = Screen.Profile.route) {
                    ProfileScreen(navController)
                }
                composable(route = "${Screen.FavoriteDetails.route}/{id}"
                ){ backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")
                    id?.let { FavoriteDetailsScreen(navController,it) }
                }
            }
        }
    )
}
