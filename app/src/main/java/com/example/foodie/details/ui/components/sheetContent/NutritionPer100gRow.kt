package com.example.foodie.details.ui.components.sheetContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.foodie.R


@Composable
fun NutrientInfoRowPer100g(totalQuantity: Float, color: Int, totalCalories: Float, title: String) {
    val context = LocalContext.current
    val arcColor = Color(ContextCompat.getColor(context, color))

    // Рассчитываем граммы, исходя из общего количества и калорий
    val calculatedGrams = (totalQuantity / totalCalories) * 360

    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHighest),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(arcColor)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.primary_gray),
            )
        }
        Text(
            text = String.format("%.0fg", calculatedGrams),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.primary_gray),
            modifier = Modifier.padding(end = 10.dp)
        )
    }
}

