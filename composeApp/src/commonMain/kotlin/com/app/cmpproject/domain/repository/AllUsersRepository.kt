package com.app.cmpproject.domain.repository

import com.app.cmpproject.data.model.AllUsersResponse
import com.app.cmpproject.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AllUsersRepository {
    suspend fun allUsers(): Flow<NetworkResult<AllUsersResponse>>
}