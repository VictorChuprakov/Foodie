package com.example.foodie.common.ui.bottomNavigation

data class BottomNavItem(
    val route: String?,
    val icon: Int?, // Иконка может быть нулевой для "Scan"
    val label: Int
)