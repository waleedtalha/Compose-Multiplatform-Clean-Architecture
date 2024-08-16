package com.app.cmpproject.data.repository

import com.app.cmpproject.data.model.LoginResponse
import com.app.cmpproject.data.remote.NetworkResult
import com.app.cmpproject.data.remote.RemoteDataSource
import com.app.cmpproject.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(private val remoteDataSource: RemoteDataSource) : LoginRepository {
    override suspend fun loginUser(
        username: String,
        password: String
    ): Flow<NetworkResult<LoginResponse>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.login(username,password)
            emit(response)
        }
    }

}