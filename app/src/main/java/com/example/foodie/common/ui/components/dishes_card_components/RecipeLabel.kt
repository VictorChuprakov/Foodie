package com.example.foodie.common.ui.components.dishes_card_components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.foodie.R

@Composable
fun RecipeLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
        color = colorResource(id = R.color.primary_blue),
        modifier = Modifier.width(150.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
