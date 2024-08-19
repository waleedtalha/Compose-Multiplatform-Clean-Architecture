package com.app.cmpproject.presentation.screens.users

import com.app.cmpproject.data.model.AllUsersResponse

sealed interface AllUsersScreenState {
    data object Loading : AllUsersScreenState

    data object Idle : AllUsersScreenState

    data class Success(val data: AllUsersResponse) : AllUsersScreenState

    data class Error(val errorMessage: String) : AllUsersScreenState
}