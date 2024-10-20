package com.example.foodie.common.ui.components.dishes_card_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.foodie.R

@Composable
fun RecipeDetails(mealType: List<String>,totalTime: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = mealType.joinToString(", ") { it.replaceFirstChar { char -> char.titlecase() } },
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.primary_gray),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.width(5.dp))
        DotIndicator()
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = if (totalTime >= 60.0) ">60 mins" else ">${totalTime.toInt()} mins",
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.primary_gray),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun DotIndicator() {
    Box(
        modifier = Modifier
            .size(5.dp)
            .clip(CircleShape)
            .background(colorResource(id = R.color.primary_gray))
    )
}
