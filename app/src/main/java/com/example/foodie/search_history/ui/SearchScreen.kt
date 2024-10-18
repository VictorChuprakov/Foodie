package com.example.foodie.search_history.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.common.data.room.SearchQuery
import com.example.foodie.common.ui.SharedViewModel
import com.example.foodie.common.ui.bottomNavigation.Routes

@Composable
fun SearchScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val historySearch by sharedViewModel.historySearch.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        items(historySearch.reversed()) { history ->
            SearchItem(
                query = history,
                onSaveLastQuery = {
                    sharedViewModel.saveLastQuery(history.query)
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
                onNavigate = {
                    navController.navigate(Routes.dishes)
                }
            )
        }
    }
}



@Composable
private fun SearchItem(
    query: SearchQuery,
    onSaveLastQuery: () -> Unit,  // Передаём лямбду для сохранения
    onNavigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_history_circle),
                contentDescription = null,
                tint = colorResource(R.color.primary_gray)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = query.query,
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.primary_blue)
            )
        }
        IconButton(onClick = {
            onSaveLastQuery()
            onNavigate()
        }) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_upward),
                contentDescription = null,
                tint = colorResource(R.color.primary_gray),
            )
        }
    }
}