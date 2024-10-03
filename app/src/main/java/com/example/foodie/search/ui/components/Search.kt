package com.example.foodie.search.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.search.ui.SearchViewModel

@Composable
fun Search(searchViewModel: SearchViewModel) {
    var isFocused by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(15.dp)

    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { state -> isFocused = state.isFocused },
            value = query,
            onValueChange = { query = it },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    color = colorResource(id = R.color.primary_gray),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            shape = CircleShape,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = if (isFocused) colorResource(id = R.color.primary_gray) else colorResource(
                        id = R.color.primary_blue
                    )
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search // Устанавливаем действие "Поиск" для клавиатуры
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (query.isNotEmpty()) {
                        searchViewModel.getFoods(query)
                        searchViewModel.saveQuery(query) // Сохраняем в базе данных
                        keyboardController?.hide() // Скрываем клавиатуру после поиска
                        focusManager.clearFocus() // Снимаем фокус
                        query = ""
                    }
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.transparent),
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                focusedTextColor = colorResource(id = R.color.primary_gray),
                focusedPlaceholderColor = colorResource(id = R.color.primary_gray),

                unfocusedBorderColor = colorResource(id = R.color.transparent),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                unfocusedTextColor = colorResource(id = R.color.primary_gray),
                unfocusedPlaceholderColor = colorResource(id = R.color.primary_gray)
            ),
            singleLine = true
        )
        AnimatedVisibility(
            visible = isFocused,
        ) {
            Text(
                text = "Отмена",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .clickable {
                        isFocused = false
                        focusManager.clearFocus()
                        query = ""
                    }
                    .padding(start = 10.dp)
            )
        }
    }
}