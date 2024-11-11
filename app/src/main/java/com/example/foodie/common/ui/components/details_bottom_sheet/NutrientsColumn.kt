package com.example.foodie.common.ui.components.details_bottom_sheet

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.common.data.model.TotalNutrients
import com.example.foodie.common.ui.ColorNutrition


@Composable
fun NutrientsColumn(totalNutrients: TotalNutrients,calories: Double) {
    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = stringResource(R.string.nutrition_100g),
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        color = MaterialTheme.colorScheme.primary
    )
    Column {
        NutrientInfoRowPer100g(
            totalNutrients.FAT.quantity.toFloat(),
            ColorNutrition.RED.color,
            calories.toFloat(),
            totalNutrients.FAT.label
        )
        NutrientInfoRowPer100g(
            totalNutrients.CHOCDF.quantity.toFloat(),
            ColorNutrition.YELLOW.color,
            calories.toFloat(),
            totalNutrients.CHOCDF.label
        )
        NutrientInfoRowPer100g(totalNutrients.PROCNT.quantity.toFloat(),
            ColorNutrition.GREEN.color,
            calories.toFloat(),
            totalNutrients.PROCNT.label
        )
    }
}


@SuppressLint("DefaultLocale")
@Composable
private fun NutrientInfoRowPer100g(totalQuantity: Float, color: Color, totalCalories: Float, title: String) {
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
                    .background(color)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
        Text(
            text = String.format("%.0fg", calculatedGrams),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(end = 10.dp)
        )
    }
}

