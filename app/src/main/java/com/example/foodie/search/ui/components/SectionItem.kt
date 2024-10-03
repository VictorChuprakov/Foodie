package com.example.foodie.search.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.common.data.room.SearchQuery

@Composable
fun SearchItem(
    query: SearchQuery,
    isSelected: Boolean,
    onSearchSelected: (String) -> Unit,
    onDeleteSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(if (isSelected) colorResource(R.color.primary_green) else colorResource(R.color.primary_form))
            .padding(15.dp)
            .clickable { onSearchSelected(query.query) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = query.query,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSelected) colorResource(id = R.color.white) else colorResource(id = R.color.primary_gray)
        )
        Image(
            painter = painterResource(R.drawable.ic_close_round),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(20.dp)
                .clickable(onClick = onDeleteSelected),
            colorFilter = ColorFilter.tint(colorResource(R.color.primary_blue))
        )
    }
}