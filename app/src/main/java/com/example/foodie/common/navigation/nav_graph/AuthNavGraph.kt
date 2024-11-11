package com.example.foodie.common.navigation.nav_graph

import PasswordRecoveryScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodie.authorization.new_password.ui.NewPasswordScreen
import com.example.foodie.authorization.password_verification_code.ui.PasswordVerificationCodeScreen
import com.example.foodie.authorization.sing_in.ui.SingInScreen
import com.example.foodie.authorization.sing_up.ui.SingUpScreen
import com.example.foodie.authorization.verification_code.ui.VerificationCodeScreen
import com.example.foodie.common.navigation.AUTH_GRAPH_ROUTE
import com.example.foodie.common.navigation.Screen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.SingIn.route,
        route = AUTH_GRAPH_ROUTE
    ){
        composable(
            route = Screen.SingIn.route
        ) {
            SingInScreen(navController = navController)
        }
        composable(
            route = Screen.SingUp.route
        ) {
            SingUpScreen(navController = navController)
        }
        composable(
            route = Screen.VerificationCode.route
        ) {
            VerificationCodeScreen()
        }
        composable(
            route = Screen.PasswordRecovery.route
        ) {
            PasswordRecoveryScreen()
        }
        composable(
            route = Screen.PasswordVerificationCode.route
        ) {
            PasswordVerificationCodeScreen()
        }
        composable(
            route = Screen.NewPassword.route
        ) {
            NewPasswordScreen()
        }
    }
}