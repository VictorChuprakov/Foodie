package com.example.foodie.authorization.new_password.ui

import com.example.foodie.authorization.components.AuthHeader
import com.example.foodie.authorization.components.ButtonAuth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.authorization.components.PasswordTextField
import com.example.foodie.authorization.components.PasswordValidation
import com.example.foodie.authorization.components.isValidPassword

@Composable
fun NewPasswordScreen() {
    var password by remember { mutableStateOf("") }
    val isPasswordValid by remember { derivedStateOf { isValidPassword(password) } }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AuthHeader(R.string.reset_password, R.string.enter_new_password)
        Spacer(modifier = Modifier.height(30.dp))
        PasswordTextField(password = password, onPasswordChange = { password = it })
        PasswordValidation(password = password)
        Spacer(modifier = Modifier.height(20.dp))
        ButtonAuth(
            text = R.string.sign_up,
            onClick = { TODO()},
            enabled = isPasswordValid
        )
    }
}
