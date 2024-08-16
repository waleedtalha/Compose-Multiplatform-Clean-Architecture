package com.app.cmpproject.presentation.screens.users

import com.app.cmpproject.data.model.AllUsersResponse

sealed interface AllUsersScreenState {
    data class Loading(val isLoading: Boolean) : AllUsersScreenState

    data class Success(val data: AllUsersResponse) : AllUsersScreenState

    data class Error(val errorMessage: String) : AllUsersScreenState
}