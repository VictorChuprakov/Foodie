package com.example.foodie.common.ui.components.details_bottom_sheet

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodie.R

@Composable
fun IngredientsList(ingredientLines: List<String>) {
    Spacer(modifier = Modifier.height(10.dp))
    Column {
        Text(
            text = stringResource(R.string.ingredients),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(10.dp))
        ingredientLines.forEach { ingredient ->
            IngredientItem(capitalizeFirstLetter(ingredient))
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
    Divider()
}

@Composable
private fun IngredientItem(title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_check_active),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}


private fun capitalizeFirstLetter(text: String): String {
    // Найдем первую букву, игнорируя цифры
    val firstLetterIndex = text.indexOfFirst { it.isLetter() }
    return if (firstLetterIndex != -1) {
        val firstPart = text.substring(0, firstLetterIndex) // Часть до первой буквы
        val firstLetter = text[firstLetterIndex].uppercase() // Заглавная буква
        val restPart = text.substring(firstLetterIndex + 1) // Остальная часть строки
        firstPart + firstLetter + restPart // Соберем строку
    } else {
        text // Если букв нет, возвращаем строку как есть
    }
}