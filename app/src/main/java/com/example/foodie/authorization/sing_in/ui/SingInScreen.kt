package com.example.foodie.authorization.sing_in.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.foodie.R
import com.example.foodie.authorization.components.AuthHeader
import com.example.foodie.authorization.components.ButtonAuth
import com.example.foodie.authorization.components.EmailTextField
import com.example.foodie.authorization.components.PasswordTextField
import com.example.foodie.authorization.components.isValidEmail
import com.example.foodie.authorization.components.isValidPassword
import com.example.foodie.common.navigation.HOME_GRAPH_ROUTE


@Composable
fun SingInScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isEmailValid by remember { derivedStateOf { isValidEmail(email) } }
    val isPasswordValid by remember { derivedStateOf { isValidPassword(password) } }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.weight(1.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AuthHeader(R.string.welcome, R.string.enter_account_prompt)
            Spacer(modifier = Modifier.height(30.dp))
            EmailTextField(value = email, onEmailChange = { email = it })
            Spacer(modifier = Modifier.height(30.dp))
            PasswordTextField(password = password, onPasswordChange = { password = it })
            ForgotPassword(onClick = { TODO() }, modifier = Modifier.align(Alignment.End))
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ButtonAuth(
                text = R.string.sign_in,
                onClick = { TODO()},
                enabled = isEmailValid && isPasswordValid
            )
            Text(
                text = stringResource(R.string.continue_with),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            ButtonGoogle(onClick = { navController.navigate(HOME_GRAPH_ROUTE)  })
            AccountSignUpSuggestion(onClick = { TODO() })
        }
    }
}


@Composable
private fun ForgotPassword(onClick: () -> Unit, modifier: Modifier) {
    TextButton(onClick = onClick, modifier = modifier) {
        Text(
            text = stringResource(R.string.forgot_password),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


@Composable
private fun ButtonGoogle(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_google_logo), contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
        Text(
            text = stringResource(R.string.google),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun AccountSignUpSuggestion(onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(R.string.no_account_prompt),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        TextButton(
            onClick = onClick,
        ) {
            Text(
                text = stringResource(R.string.sign_up),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
