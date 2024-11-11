package com.example.foodie.common.ui.components.details_bottom_sheet

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun Divider() {
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.secondary,
    )
}
