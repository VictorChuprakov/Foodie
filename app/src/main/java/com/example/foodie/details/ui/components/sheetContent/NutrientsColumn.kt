package com.example.foodie.details.ui.components.sheetContent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.details.data.model.Hit


@Composable
fun NutrientsColumn(hit: Hit) {
    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = "Nutrition 100g",
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        color = colorResource(id = R.color.primary_blue)
    )
    Column {
        NutrientInfoRowPer100g(
            hit.recipe.totalNutrients.FAT.quantity.toFloat(),
            R.color.red,
            hit.recipe.calories.toFloat(),
            hit.recipe.totalNutrients.FAT.label
        )
        NutrientInfoRowPer100g(
            hit.recipe.totalNutrients.CHOCDF.quantity.toFloat(),
            R.color.yellow,
            hit.recipe.calories.toFloat(),
            hit.recipe.totalNutrients.CHOCDF.label
        )
        NutrientInfoRowPer100g(
            hit.recipe.totalNutrients.PROCNT.quantity.toFloat(),
            R.color.green,
            hit.recipe.calories.toFloat(),
            hit.recipe.totalNutrients.PROCNT.label
        )
    }
}
