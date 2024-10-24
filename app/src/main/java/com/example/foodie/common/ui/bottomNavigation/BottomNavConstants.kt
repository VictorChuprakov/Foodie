package com.example.foodie.common.ui.bottomNavigation

import com.example.foodie.R

object BottomNavConstants {
    val BottomNavItems = listOf(
        BottomNavItem(
            "home_screen",
            R.drawable.ic_home,
            R.string.label_home
        ),
        BottomNavItem(
            "upload_screen",
            R.drawable.ic_edit,
            R.string.label_upload
        ),
        BottomNavItem(
            "scan_screen",
            null,
            R.string.label_scan
        ),
        BottomNavItem(
            "notification_screen",
            R.drawable.ic_notification,
            R.string.label_notification
        ),
        BottomNavItem(
            "profile_screen",
            R.drawable.ic_profile,
            R.string.label_profile
        )
    )
}