package com.example.foodie.common.navigation


sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Scan: Screen(route = "scan_screen")
    object Notification: Screen(route = "notification_screen")
    object Upload: Screen(route = "upload_screen")
    object Detail : Screen(route = "detail_screen")
    object Profile : Screen(route = "profile_screen")
    object FavoriteDetails : Screen(route = "favorite_details_screen")
    object Search: Screen(route = "search_screen")
}