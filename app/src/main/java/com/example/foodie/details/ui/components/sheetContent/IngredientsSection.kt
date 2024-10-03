package com.example.foodie.details.ui.components.sheetContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.common.data.model.Hit
import com.example.foodie.details.ui.Divider

@Composable
fun IngredientsSection(hit: Hit) {
    Spacer(modifier = Modifier.height(10.dp))
    Column {
        Text(
            text = "Ingredients",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            color = colorResource(id = R.color.primary_blue)
        )
        Spacer(modifier = Modifier.height(10.dp))
        hit.recipe.ingredientLines.forEach { ingredient ->
            IngredientsItem(ingredient)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider()
}


@Composable
fun IngredientsItem(title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 8.dp),
            color = colorResource(id = R.color.primary_gray)
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}