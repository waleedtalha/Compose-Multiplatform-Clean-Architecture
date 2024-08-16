package com.app.cmpproject.presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.cmpproject.core.viewModel
import com.app.cmpproject.presentation.components.ProgressIndicator
import com.app.cmpproject.presentation.components.AppScaffold
import com.app.cmpproject.presentation.screens.users.AllUsersScreen
import kotlinx.coroutines.launch

object LoginScreen :Screen{
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel: LoginViewModel = viewModel()
        val loginState by viewModel.state.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

        AppScaffold(
            snackBarHost = { SnackbarHost(hostState = snackbarHostState) },
            content = {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Enter your details", style = MaterialTheme.typography.bodyLarge,
                        fontSize = 22.sp, fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(116.dp))

                    UsernameTextField(
                        viewModel,
                        value = viewModel.userNameInput.input,
                        onValueChange = { text ->
                            viewModel.userNameInput = viewModel.userNameInput.copy(input = text)
                        },
                        isError = viewModel.userNameInput.isError
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PasswordTextField(
                        viewModel,
                        value = viewModel.passwordInput.input,
                        onValueChange = { text ->
                            viewModel.passwordInput = viewModel.passwordInput.copy(input = text)
                        },
                        isError = viewModel.passwordInput.isError
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        if (viewModel.userNameInput.isError || viewModel.passwordInput.isError) {
                            val errorMessage = when {
                                viewModel.userNameInput.isError -> viewModel.userNameInput.errorText
                                viewModel.passwordInput.isError -> viewModel.passwordInput.errorText
                                else -> ""
                            }
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = errorMessage,
                                    duration = SnackbarDuration.Short,
                                    actionLabel = "Dismiss"
                                )
                            }
                        } else {
                            viewModel.loginUser(
                                viewModel.userNameInput.input.trim(),
                                viewModel.passwordInput.input.trim()
                            )
                        }
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Submit")
                    }
                    when (loginState) {
                        is LoginScreenState.Loading -> {
                            if ((loginState as LoginScreenState.Loading).isLoading) {
                                ProgressIndicator()
                            }
                        }

                        is LoginScreenState.Success -> {
                            navigator?.push(AllUsersScreen)
                            println((loginState as LoginScreenState.Success).data.email)
                        }

                        is LoginScreenState.Error -> {
                            coroutineScope.launch {
                                val errorMessage = (loginState as LoginScreenState.Error).errorMessage.split("\n").firstOrNull()
                                snackbarHostState.showSnackbar(
                                    message = errorMessage?:"",
                                    duration = SnackbarDuration.Short,
                                    actionLabel = "Dismiss"
                                )
                            }
                            println((loginState as LoginScreenState.Error).errorMessage)
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun UsernameTextField(
    viewModel: LoginViewModel,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = { text ->
            onValueChange(text)
            if (isError && text.isNotEmpty()) {
                viewModel.userNameInput = viewModel.userNameInput.copy(
                    isError = false,
                    errorText = ""
                )
            }
        },
        label = { Text(text = "Username") },
        isError = isError,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        )
    )
}

@Composable
fun PasswordTextField(
    viewModel: LoginViewModel,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = { text ->
            onValueChange(text)
            if (isError && text.isNotEmpty()) {
                viewModel.passwordInput = viewModel.passwordInput.copy(
                    isError = false,
                    errorText = ""
                )
            }
        },
        label = { Text(text = "Password") },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    )
}