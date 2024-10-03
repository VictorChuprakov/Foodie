package com.example.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.foodie.common.ui.bottomNavigation.NavHostContainer
import com.example.foodie.ui.theme.FoodieTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.ResourceBundle.clearCache

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodieTheme {
                val navComposable = rememberNavController()
                NavHostContainer(navController = navComposable)
            }
        }
    }
    override fun onStop() {
        super.onStop()
        clearCache()
    }
}

