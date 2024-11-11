package com.example.foodie.common.ui.bottomNavigation

import com.example.foodie.R
import com.example.foodie.common.navigation.Screen

object BottomNavConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            Screen.Home.route,
            R.drawable.ic_home,
            R.string.label_home,
        ),
        BottomNavItem(
            null,
            null,
            R.string.label_scan,
        ),
        BottomNavItem(
            Screen.Profile.route,
            R.drawable.ic_user,
            R.string.label_profile,
        )
    )
}