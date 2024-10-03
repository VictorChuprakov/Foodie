package com.example.foodie.details.ui.components.sheetContent

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.foodie.R
import com.example.foodie.common.data.model.Hit


@Composable
fun ButtonInstructions(hit: Hit) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(hit.recipe.url)
            }
            context.startActivity(intent)
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.primary_green),
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = "Instructions")
    }
}
