package com.example.foodie.search.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.foodie.R

@Composable
fun ConfirmationDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.confirmation_delete_title)) },
        text = { Text(stringResource(R.string.confirmation_delete_message)) },
        confirmButton = {
            Button(
                onClick = onConfirm,
            ) {
                Text(stringResource(R.string.delete))
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}