package com.example.foodie.common.ui.components.details_bottom_sheet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.foodie.R
import com.example.foodie.common.data.model.TotalNutrients

@Composable
fun NutrientsRow(totalNutrients: TotalNutrients) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {
        PowerTable(
            totalNutrients.PROCNT.quantity.toFloat(),
            R.color.green,
            totalNutrients.PROCNT.label
        )
        PowerTable(
            totalNutrients.CHOCDF.quantity.toFloat(),
            R.color.yellow,
            totalNutrients.CHOCDF.label
        )
        PowerTable(
            totalNutrients.FAT.quantity.toFloat(),
            R.color.red,
            totalNutrients.FAT.label
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider()
}

@Composable
private fun PowerTable(Quantity: Float, color: Int, title: String) {
    val context = LocalContext.current
    val arcColor = Color(ContextCompat.getColor(context, color))
    val circleSize = 65.dp
    val textColor = colorResource(id = R.color.primary_gray)


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        // Box с кругом и текстом внутри
        Box(
            modifier = Modifier.size(circleSize),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(circleSize)) {
                drawArc(
                    color = arcColor,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 20f)
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = Quantity.toInt().toString(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = textColor
                )
                Text(
                    text = stringResource(R.string.cal),
                    style = MaterialTheme.typography.bodySmall,
                    color = textColor
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Подпись под кругом
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
    }
}
