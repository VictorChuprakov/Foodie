package com.example.foodie.search_history.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.common.data.room.entities.SearchHistory
import com.example.foodie.common.navigation.Screen
import com.example.foodie.common.ui.SharedViewModel

@Composable
fun SearchScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val historySearch by sharedViewModel.historySearch.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    LazyColumn(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        item {
            HorizontalDivider(
                thickness = 8.dp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        items(historySearch.reversed()) { history ->
            SearchItem(
                query = history,
                onSaveLastQuery = {
                    sharedViewModel.saveLastQuery(history.query)
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
                onNavigate = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
    }
}



@Composable
private fun SearchItem(
    query: SearchHistory,
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
                imageVector = ImageVector.vectorResource(R.drawable.ic_history_circle),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = query.query,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        IconButton(onClick = {
            onSaveLastQuery()
            onNavigate()
        }) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_upward),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}