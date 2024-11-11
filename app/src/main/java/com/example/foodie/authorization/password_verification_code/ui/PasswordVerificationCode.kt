package com.example.foodie.authorization.password_verification_code.ui

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.example.foodie.R
import com.example.foodie.authorization.components.AuthHeader
import com.example.foodie.authorization.components.ButtonSendAgain
import com.example.foodie.authorization.components.OtpTextField

@Composable
fun PasswordVerificationCodeScreen() {
    var otpValue by remember { mutableStateOf("") }
    val otpCount = 4
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

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

        OtpTextField(
            otpText = otpValue,
            onOtpTextChange = { value, _ -> otpValue = value },
            modifier = Modifier.focusRequester(focusRequester),
        )

        Spacer(modifier = Modifier.height(30.dp))
        ButtonAuth(
            text = R.string.sign_up,
            onClick = { TODO() },
            enabled = otpValue.length == otpCount
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonSendAgain(onClick = { TODO() })
    }
}
