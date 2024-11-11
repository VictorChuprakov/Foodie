package com.example.foodie.authorization.sing_up.ui

import com.example.foodie.authorization.components.AuthHeader
import com.example.foodie.authorization.components.ButtonAuth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.authorization.components.EmailTextField
import com.example.foodie.authorization.components.PasswordTextField
import com.example.foodie.authorization.components.PasswordValidation
import com.example.foodie.authorization.components.isValidEmail
import com.example.foodie.authorization.components.isValidPassword
import com.example.foodie.common.navigation.Screen

@Composable
fun SingUpScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val isEmailValid by remember { derivedStateOf { isValidEmail(email) } }
    val isPasswordValid by remember { derivedStateOf { isValidPassword(password) } }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .statusBarsPadding()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            AuthHeader(R.string.welcome, R.string.enter_account_prompt)
            Spacer(modifier = Modifier.height(30.dp))
            EmailTextField(value = email, onEmailChange = { email = it })
            Spacer(modifier = Modifier.height(30.dp))
            PasswordTextField(password = password, onPasswordChange = { password = it })
            Spacer(modifier = Modifier.height(30.dp))
            NameTextField(value = name, onNameChange = { name = it })
            PasswordValidation(password = password)
            Spacer(modifier = Modifier.height(20.dp))
            ButtonAuth(
                text = R.string.sign_up,
                onClick = { navController.navigate(Screen.VerificationCode.route) },
                enabled = isEmailValid && isPasswordValid  && name.isNotEmpty()
            )
        }
    }
}




@Composable
private fun NameTextField(value: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = { onNameChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_user),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondaryContainer
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = Color.Transparent,
            unfocusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        keyboardActions = KeyboardActions(onNext = {}),
        singleLine = true,
        shape = CircleShape,
    )
}
