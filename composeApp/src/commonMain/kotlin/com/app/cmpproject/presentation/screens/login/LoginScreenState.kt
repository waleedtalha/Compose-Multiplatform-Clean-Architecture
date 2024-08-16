package com.app.cmpproject.presentation.screens.login

import com.app.cmpproject.data.model.LoginResponse

sealed interface LoginScreenState {
    data class Loading(val isLoading: Boolean) : LoginScreenState

    data class Success(val data: LoginResponse) : LoginScreenState

    data class Error(val errorMessage: String) : LoginScreenState
}