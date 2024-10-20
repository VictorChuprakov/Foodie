package com.example.foodie.common.ui.components.details_bottom_sheet

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.foodie.R

@Composable
fun Divider() {
    HorizontalDivider(
        thickness = 1.5.dp,
        color = colorResource(id = R.color.primary_gray),
    )
}
