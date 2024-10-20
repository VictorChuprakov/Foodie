package com.example.foodie.common.ui.bottomNavigation

import com.example.foodie.R

object BottomNavConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            "home_screen",
            R.drawable.ic_dishes,
            R.string.label_dishes
        ),
        BottomNavItem(
            "profile_screen",
            R.drawable.ic_profile,
            R.string.label_profile
        )
    )
}