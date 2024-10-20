package com.example.foodie.common.ui.components.details_bottom_sheet

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.foodie.R
import com.example.foodie.common.data.model.TotalNutrients


@Composable
fun NutrientsColumn(totalNutrients: TotalNutrients,calories: Double) {
    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = stringResource(R.string.nutrition_100g),
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        color = colorResource(id = R.color.primary_blue)
    )
    Column {
        NutrientInfoRowPer100g(
            totalNutrients.FAT.quantity.toFloat(),
            R.color.red,
            calories.toFloat(),
            totalNutrients.FAT.label
        )
        NutrientInfoRowPer100g(
            totalNutrients.CHOCDF.quantity.toFloat(),
            R.color.yellow,
            calories.toFloat(),
            totalNutrients.CHOCDF.label
        )
        NutrientInfoRowPer100g(totalNutrients.PROCNT.quantity.toFloat(),
            R.color.green,
            calories.toFloat(),
            totalNutrients.PROCNT.label
        )
    }
}


@Composable
private fun NutrientInfoRowPer100g(totalQuantity: Float, color: Int, totalCalories: Float, title: String) {
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

