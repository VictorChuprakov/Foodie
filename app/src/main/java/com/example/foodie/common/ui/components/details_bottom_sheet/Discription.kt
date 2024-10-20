package com.example.foodie.common.ui.components.details_bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodie.R

@Composable
fun Discription(label: String, totalTime: Double,mealType: List<String>) {
    Text(
        text = label,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
        color = colorResource(id = R.color.primary_blue)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = mealType.joinToString(", ") { it.replaceFirstChar { char -> char.titlecase() } },
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.primary_gray)
        )
        Spacer(modifier = Modifier.width(5.dp))
        DotIndicator()
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = if (totalTime >= 60.0) ">60 mins" else ">${totalTime.toInt()} mins",
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.primary_gray)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider()
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
