package com.example.foodie.common.navigation


sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Detail : Screen(route = "detail_screen")
    object Profile : Screen(route = "profile_screen")
    object FavoriteDetails : Screen(route = "favorite_details_screen")
    object Login: Screen(route = "login_screen")
    object Search: Screen(route = "search_screen")
}