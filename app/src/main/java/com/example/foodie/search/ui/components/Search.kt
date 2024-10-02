package com.example.foodie.search.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import com.example.foodie.R

@Composable
fun Search() {
    var isFocused by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)

    ) {
        AnimatedVisibility(visible = isFocused) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)

                )
            }
        }
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
            trailingIcon = {
                if (isFocused) {
                    AnimatedVisibility(
                        visible = isFocused,
                        enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                        exit = fadeOut(animationSpec = tween(durationMillis = 300))
                    ) {
                        IconButton(onClick = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                            isFocused = false
                            query = ""
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cancel),
                                contentDescription = null,
                                tint = colorResource(id = R.color.primary_blue),

                                )
                        }
                    }
                }
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = if (isFocused) colorResource(id = R.color.primary_gray) else colorResource(
                        id = R.color.primary_blue
                    )
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.transparent),
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                focusedTextColor = colorResource(id = R.color.primary_blue),
                focusedPlaceholderColor = colorResource(id = R.color.primary_gray),

                unfocusedBorderColor = colorResource(id = R.color.transparent),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                unfocusedTextColor = colorResource(id = R.color.primary_blue),
                unfocusedPlaceholderColor = colorResource(id = R.color.primary_gray)
            ),
            singleLine = true
        )
        AnimatedVisibility(visible = isFocused) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
        }
    }
}

