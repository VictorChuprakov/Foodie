package com.example.foodie.search.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.SharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(navController: NavController, sharedViewModel: SharedViewModel) {
    var isFocused by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val scope = rememberCoroutineScope()
    val selectMealType by sharedViewModel.lastMealType.collectAsState()
    var lastMealType by remember { mutableStateOf(selectMealType) }
    val selectedType by sharedViewModel.lastTime.collectAsState()
    var sliderPosition by remember { mutableFloatStateOf(selectedType) }

    LaunchedEffect(selectMealType, selectedType) {
        lastMealType = selectMealType
        sliderPosition = selectedType


    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
            .statusBarsPadding()
    ) {
        AnimatedVisibility(visible = isFocused) {
            IconButton(onClick = {
                navController.popBackStack()
                keyboardController?.hide()
                focusManager.clearFocus(true)
            }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    if (focusState.isFocused) {
                        navController.navigate(Screen.Search.route)
                    }
                },
            value = query,
            onValueChange = { query = it },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
            },
            shape = CircleShape,
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondaryContainer
                )

            },
            trailingIcon = {
                AnimatedVisibility(visible = isFocused) {
                    IconButton(onClick = {
                        query = ""
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondaryContainer
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (query.isNotEmpty()) {
                        sharedViewModel.saveQuery(query)
                        navController.navigate(Screen.Home.route)
                        keyboardController?.hide()
                        focusManager.clearFocus(true)
                    }
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                focusedContainerColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor =  Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
            ),
            singleLine = true
        )

        AnimatedVisibility(visible = isFocused) {
            IconButton(onClick = { scope.launch { sheetState.show() } }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_filter),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }

    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }
            },
            sheetMaxWidth = Dp.Infinity,
            modifier = Modifier.wrapContentHeight(),
            content = {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                ) {
                    TitleText(title = stringResource(R.string.category_meal_type))
                    Spacer(modifier = Modifier.height(20.dp))
                    CategoryList(
                        list = Category.entries,
                        selectedMealType = lastMealType,
                        onItemSelected = { selectedType -> lastMealType = selectedType }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TitleTextSlider()
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomSlider(
                        sliderPosition = sliderPosition,
                        onSliderChange = { sliderPosition = it }
                    )
                    SliderValueDisplay(sliderPosition = sliderPosition)
                    Spacer(modifier = Modifier.height(20.dp))
                    ActionButtons(
                        onCancel = {
                            scope.launch {
                                sheetState.hide()
                            }
                        },
                        onDone = {
                            scope.launch {
                                sheetState.hide()
                            }
                            sharedViewModel.saveFilter(lastMealType, sliderPosition)
                            navController.navigate(Screen.Home.route)
                            focusManager.clearFocus(true)
                        }
                    )
                }
            }
        )
    }
}

@Composable
private fun TitleTextSlider() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.cooking_duration),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = stringResource(R.string.in_minutes),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun SliderValueDisplay(
    sliderPosition: Float,
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = stringResource(R.string.duration_10),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primaryContainer,
        )
        Text(
            text = stringResource(R.string.duration_60),
            style = MaterialTheme.typography.titleMedium,
            color = if (sliderPosition == 60f) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun TitleText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 15.dp)
    )
}

@Composable
private fun CategoryList(
    list: List<Category>,
    selectedMealType: String,
    onItemSelected: (String) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(list) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        2.dp,
                        color = if (selectedMealType == item.apiName) Color.Transparent
                        else MaterialTheme.colorScheme.outline,
                        shape = CircleShape
                    )
                    .background(
                        if (selectedMealType == item.apiName) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    )
                    .clickable {
                        onItemSelected(item.apiName)
                    }
            ) {
                Text(
                    text = stringResource(item.displayNameResId),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (selectedMealType == item.apiName) MaterialTheme.colorScheme.onPrimaryContainer
                    else MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 25.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomSlider(
    sliderPosition: Float,
    onSliderChange: (Float) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

) {
    Slider(
        value = sliderPosition,
        onValueChange = onSliderChange,
        valueRange = 10f..60f,
        interactionSource = interactionSource,
        colors = SliderDefaults.colors(
            activeTrackColor = MaterialTheme.colorScheme.primaryContainer,
            inactiveTrackColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        thumb = {
            Label(
                label = {
                    Text(
                        text = sliderPosition.toInt().toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primaryContainer,
                    )

                },
                interactionSource = interactionSource
            ) {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    colors = SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.primaryContainer)
                )
            }
        }
    )
}


@Composable
private fun ActionButtons(
    onCancel: () -> Unit,
    onDone: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = onCancel,
            contentPadding = PaddingValues(15.dp),
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = stringResource(R.string.cancel),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Button(
            onClick = onDone,
            contentPadding = PaddingValues(15.dp),
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                text = stringResource(R.string.done),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}




