package com.example.foodie.common.ui.bottomNavigation

import com.example.foodie.R
import com.example.foodie.common.navigation.Screen

object BottomNavConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            Screen.Home.route,
            R.drawable.ic_home,
            R.string.label_home
        ),
        BottomNavItem(
            Screen.Upload.route,
            R.drawable.ic_edit,
            R.string.label_upload
        ),
        BottomNavItem(
            Screen.Scan.route,
            null,
            R.string.label_scan
        ),
        BottomNavItem(
            Screen.Notification.route,
            R.drawable.ic_notification,
            R.string.label_notification
        ),
        BottomNavItem(
            Screen.Profile.route,
            R.drawable.ic_profile,
            R.string.label_profile
        )
    )
}