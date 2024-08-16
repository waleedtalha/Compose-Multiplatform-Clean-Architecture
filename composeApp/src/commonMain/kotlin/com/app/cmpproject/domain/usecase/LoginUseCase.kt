package com.app.cmpproject.domain.usecase

import com.app.cmpproject.data.model.LoginResponse
import com.app.cmpproject.data.remote.NetworkResult
import com.app.cmpproject.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(username: String, pass: String): Flow<NetworkResult<LoginResponse>> {
        return loginRepository.loginUser(username, pass)
    }
}