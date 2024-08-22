package com.app.cmpproject.domain.repository

import com.app.cmpproject.data.model.LoginResponse
import com.app.cmpproject.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * interface to make an interaction between [LoginRepositoryImpl] & [LoginUseCase]
 * */
interface LoginRepository {
    suspend fun loginUser(username: String, password: String): Flow<NetworkResult<LoginResponse>>
}