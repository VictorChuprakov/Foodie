package com.example.foodie.common.ui.components.details_bottom_sheet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.common.data.model.TotalNutrients

@Composable
fun NutritionPieChart(calories: Double, totalNutrients: TotalNutrients) {
    val place = totalNutrients
    val total =
        place.PROCNT.quantity.toFloat() + place.FAT.quantity.toFloat() + place.CHOCDF.quantity.toFloat()

    val substances = listOf(
        (place.PROCNT.quantity.toFloat() / total * 360),
        (place.CHOCDF.quantity.toFloat() / total * 360),
        (place.FAT.quantity.toFloat() / total * 360),
    )

    val color = listOf(
        colorResource(id = R.color.green),
        colorResource(id = R.color.yellow),
        colorResource(id = R.color.red),
    )

    Spacer(modifier = Modifier.height(10.dp))
    Text(
        text = "Nutrition Summary",
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        color = colorResource(id = R.color.primary_blue)
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(190.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(170.dp)
        ) {
            val width = size.width
            val height = size.height
            var startAngle = 0f
            substances.forEachIndexed { index, sweepAngle ->
                drawArc(
                    color = color[index],
                    useCenter = false,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    size = Size(170.dp.toPx(), 170.dp.toPx()),
                    style = Stroke(
                        width = 30f
                    ),
                    topLeft = Offset((width - 170.dp.toPx()) / 2, (height - 170.dp.toPx()) / 2)
                )
                startAngle += sweepAngle
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = calories.toInt().toString(),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
                color = colorResource(id = R.color.primary_gray)
            )
            Text(
                text = stringResource(R.string.calories),
                style = MaterialTheme.typography.titleSmall,
                color = colorResource(id = R.color.primary_gray)
            )
        }
    }
}
