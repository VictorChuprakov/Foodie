package com.example.foodie.details.ui.components.sheetContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.common.data.model.Hit

@Composable
fun NutrientsRow(hit: Hit) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ) {
        PowerTable(
            hit.recipe.totalNutrients.PROCNT.quantity.toFloat(),
            R.color.green,
            hit.recipe.totalNutrients.PROCNT.label
        )
        PowerTable(
            hit.recipe.totalNutrients.CHOCDF.quantity.toFloat(),
            R.color.yellow,
            hit.recipe.totalNutrients.CHOCDF.label
        )
        PowerTable(
            hit.recipe.totalNutrients.FAT.quantity.toFloat(),
            R.color.red,
            hit.recipe.totalNutrients.FAT.label
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider()
}