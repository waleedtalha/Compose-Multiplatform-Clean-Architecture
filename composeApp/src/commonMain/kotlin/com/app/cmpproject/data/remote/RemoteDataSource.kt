package com.app.cmpproject.data.remote

import com.app.cmpproject.data.model.AllUsersResponse
import com.app.cmpproject.data.model.LoginResponse
import kotlinx.coroutines.flow.Flow

abstract class RemoteDataSource {
    abstract suspend fun login(
        username: String,
        password: String
    ): NetworkResult<LoginResponse>

    abstract suspend fun allUsers(): NetworkResult<AllUsersResponse>
}