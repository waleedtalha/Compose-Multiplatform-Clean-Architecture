package com.app.cmpproject.presentation.screens.login

import com.app.cmpproject.data.model.LoginResponse

sealed interface LoginScreenState {
    data object Loading : LoginScreenState

    data object Idle : LoginScreenState

    data class Success(val data: LoginResponse) : LoginScreenState

    data class Error(val errorMessage: String) : LoginScreenState
}