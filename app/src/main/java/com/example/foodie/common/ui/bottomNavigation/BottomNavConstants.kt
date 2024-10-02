package com.example.foodie.common.ui.bottomNavigation

import com.example.foodie.R

object BottomNavConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            "dishes",
            R.drawable.ic_dishes,
            R.string.label_dishes
        ),
        BottomNavItem(
            "search",
            R.drawable.ic_search,
            R.string.label_search
        ),
        BottomNavItem(
            "profile",
            R.drawable.ic_profile,
            R.string.label_profile
        )
    )
}