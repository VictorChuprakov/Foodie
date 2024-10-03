package com.example.foodie.search.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodie.common.data.room.SearchQuery

@Composable
fun SearchHistory(
    searchQuery: List<SearchQuery>,
    onSearchSelected: (String) -> Unit,
    onDeleteSelected: (Long) -> Unit,
    onDeleteAll: () -> Unit,
) {
    var selectedQuery by remember { mutableStateOf<String?>(null) }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    LaunchedEffect(searchQuery) {
        selectedQuery = searchQuery.lastOrNull()?.query
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Header(onDeleteAll = { showConfirmationDialog = true })
        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(vertical = 10.dp)) {
            items(searchQuery.reversed()) { search ->
                val isSelected = search.query == selectedQuery
                SearchItem(
                    query = search,
                    isSelected = isSelected,
                    onSearchSelected = {
                        selectedQuery = search.query
                        onSearchSelected(it)
                    },
                    onDeleteSelected = { onDeleteSelected(search.id) }
                )
            }
        }
    }
    if (showConfirmationDialog) {
        ConfirmationDialog(
            onConfirm = {
                onDeleteAll()
                showConfirmationDialog = false
            },
            onDismiss = { showConfirmationDialog = false }
        )
    }
}





