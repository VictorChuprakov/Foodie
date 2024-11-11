package com.example.foodie.common.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val ON_BOARDING_GRAPH_ROTE = "boarding"
const val HOME_GRAPH_ROUTE = "home"


sealed class Screen(val route: String) {
    data object Home : Screen(route = "home_screen")
    data object Detail : Screen(route = "detail_screen")
    data object Profile : Screen(route = "profile_screen")
    data object FavoriteDetails : Screen(route = "favorite_details_screen")
    data object SingIn: Screen(route = "sing_in_screen")
    data object Search: Screen(route = "search_screen")
    data object OnBoarding: Screen(route = "on_boarding_screen")
    data object SingUp: Screen(route = "sing_up_screen")
    data object VerificationCode: Screen(route = "verification_code_screen")
    data object PasswordRecovery: Screen(route = "password_recovery_screen")
    data object NewPassword: Screen(route = "new_password_screen")
    data object Main: Screen(route = "main_screen")
    data object PasswordVerificationCode: Screen(route = "password_verification_code_screen")
}