package com.app.cmpproject.data.repository

import com.app.cmpproject.data.model.AllUsersResponse
import com.app.cmpproject.data.remote.NetworkResult
import com.app.cmpproject.data.remote.RemoteDataSource
import com.app.cmpproject.domain.repository.AllUsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AllUsersRepositoryImpl(private val remoteDataSource: RemoteDataSource) : AllUsersRepository {
    override suspend fun allUsers(): Flow<NetworkResult<AllUsersResponse>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.allUsers()
            emit(response)
        }
    }
}