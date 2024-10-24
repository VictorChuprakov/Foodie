package com.example.foodie.common.ui.bottomNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foodie.R


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 0.dp // Убираем тень для чистого фона
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavConstants.BottomNavItems.forEach { navItem ->
            if (navItem.route == "scan_screen") {
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    label = {
                        Text(
                            text = stringResource(id = navItem.label),
                            color = colorResource(id = R.color.primary_gray),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                            modifier = Modifier.padding(top = 20.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    ),
                )
            } else {
                NavigationBarItem(
                    selected = currentRoute == navItem.route,
                    onClick = {
                        if (navItem.route != null) {
                            navController.navigate(navItem.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        if (navItem.icon != null) {
                            Icon(
                                painter = painterResource(id = navItem.icon),
                                contentDescription = null,
                                tint = if (currentRoute == navItem.route) {
                                    colorResource(id = R.color.primary_green)
                                } else {
                                    colorResource(id = R.color.primary_gray)
                                }
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = navItem.label),
                            color = if (currentRoute == navItem.route) {
                                colorResource(id = R.color.primary_green)
                            } else {
                                colorResource(id = R.color.primary_gray)
                            },
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent
                    ),
                )
            }
        }
    }
}

