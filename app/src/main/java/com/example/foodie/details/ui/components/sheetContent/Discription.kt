package com.example.foodie.details.ui.components.sheetContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.common.ui.components.CircleDot
import com.example.foodie.common.data.model.Hit

@Composable
fun Discription(hit: Hit) {
    Text(
        text = hit.recipe.label,
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
            text = hit.recipe.mealType.joinToString(", "),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.primary_gray)
        )
        Spacer(modifier = Modifier.width(5.dp))
        CircleDot()
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = if (hit.recipe.totalTime == 0.0) "> ∞ mins" else "> ${hit.recipe.totalTime.toInt()} mins",
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.primary_gray)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider()
}