package com.example.foodie.search.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ModalBottomSheetProperties
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.common.ui.bottomNavigation.Routes
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
    val lastMealType = remember { mutableStateOf(selectMealType) }
    val selectedType by sharedViewModel.lastTime.collectAsState()
    var sliderPosition = remember { mutableStateOf(selectedType) }

    LaunchedEffect(selectMealType,selectedType) {
        lastMealType.value = selectMealType
        sliderPosition.value = selectedType


    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
            .statusBarsPadding()
    ) {
        AnimatedVisibility(visible = isFocused) {
            IconButton(onClick = { navController.popBackStack() }) {
                Image(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = null
                )
            }
        }
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    if (focusState.isFocused) {
                        navController.navigate(Routes.search)
                    }
                },
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
            trailingIcon = {
                AnimatedVisibility(visible = isFocused) {
                    IconButton(onClick = {
                        query = ""
                        focusManager.clearFocus(true)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = null,
                            tint = colorResource(R.color.primary_blue)
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
                        navController.navigate(Routes.dishes)
                        keyboardController?.hide()
                        focusManager.clearFocus(true)
                        isFocused = true
                    }
                }
            ),
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
            IconButton(onClick = { scope.launch { sheetState.show() } }) {
                Image(painter = painterResource(R.drawable.ic_filter), contentDescription = null)
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
                    TitleText(text = "Сatehory MealType")
                    Spacer(modifier = Modifier.height(20.dp))
                    CategoryList(
                        list = Category.entries,
                        selectedMealType = lastMealType.value,
                        onItemSelected = { selectedType -> lastMealType.value = selectedType }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TitleTextSlider()
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomSlider(
                        sliderPosition = sliderPosition.value,
                        onSliderChange = { sliderPosition.value = it }
                    )
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
                            sharedViewModel.saveFilter(lastMealType.value, sliderPosition.value)
                            navController.navigate(Routes.dishes)
                            focusManager.clearFocus(true)
                            isFocused = true
                        }
                    )
                }
            }
        )
    }
}

@Composable
private fun TitleTextSlider() {
    Text(
        buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.titleLarge.toSpanStyle().copy(
                    color = colorResource(R.color.primary_blue),
                    fontWeight = FontWeight.ExtraBold
                )
            ) {
                append("Cooking Duration ")
            }
            withStyle(
                style = MaterialTheme.typography.titleLarge.toSpanStyle().copy(
                    color = colorResource(R.color.primary_gray),
                )
            ) {
                append("(in minutes)")
            }
        }

    )
}

@Composable
private fun TitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold),
        color = colorResource(R.color.primary_blue),
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
                        color = if (selectedMealType == item.apiName) colorResource(R.color.transparent)
                        else MaterialTheme.colorScheme.outlineVariant,
                        shape = CircleShape
                    )
                    .background(
                        if (selectedMealType == item.apiName) colorResource(R.color.primary_green)
                        else MaterialTheme.colorScheme.surfaceContainerHigh,
                        shape = CircleShape
                    )
                    .clickable {
                        onItemSelected(item.apiName)
                    }
            ) {
                Text(
                    text = stringResource(item.displayNameResId),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (selectedMealType == item.apiName) colorResource(R.color.white)
                    else colorResource(R.color.primary_gray),
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
            activeTrackColor = colorResource(R.color.primary_green),
            inactiveTrackColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        thumb = {
            Label(
                label = {
                    Text(
                        text = sliderPosition.toInt().toString(),
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(R.color.primary_green),
                    )

                },
                interactionSource = interactionSource
            ) {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    colors = SliderDefaults.colors(thumbColor = colorResource(R.color.primary_green))
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
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            )
        ) {
            Text(
                text = "Cancel",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(R.color.primary_blue),
                modifier = Modifier.padding(10.dp)

            )
        }
        Button(
            onClick = onDone,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_green))
        ) {
            Text(
                text = "Done",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}




