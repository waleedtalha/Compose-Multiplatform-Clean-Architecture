package com.app.cmpproject.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.app.cmpproject.core.ViewModel
import com.app.cmpproject.data.model.LoginValidation
import com.app.cmpproject.data.remote.ApiStatus
import com.app.cmpproject.domain.usecase.LoginUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel {
    //below are the by default credentials for login
    var userNameInput by mutableStateOf(LoginValidation("emilys"))
    var passwordInput by mutableStateOf(LoginValidation("emilyspass"))

    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState.Idle)
    var state = _state.asStateFlow()

    fun loginUser(username: String, pass: String) {
        if (userNameInput.input.isEmpty() || userNameInput.input.isBlank()) {
            userNameInput = userNameInput.copy(isError = true, errorText = "Please Enter Username")
            return
        }
        if (passwordInput.input.isEmpty() || passwordInput.input.isBlank()) {
            passwordInput = passwordInput.copy(isError = true, errorText = "Please Enter Password")
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            loginUseCase.invoke(username, pass).collect { response ->
                when (response.status) {
                    ApiStatus.LOADING -> {
                        _state.update {
                            LoginScreenState.Loading
                        }
                    }

                    ApiStatus.SUCCESS -> {
                        response.data?.let {
                            _state.update {
                                LoginScreenState.Success(response.data)
                            }
                        } ?: run {
                            _state.update {
                                LoginScreenState.Error("Unknown error occurred")
                            }
                        }
                    }

                    ApiStatus.ERROR -> {
                        _state.update {
                            LoginScreenState.Error(response.message.toString())
                        }
                    }
                }

            }
        }
    }

    fun resetState(){
        _state.update {
            LoginScreenState.Idle
        }
    }

}