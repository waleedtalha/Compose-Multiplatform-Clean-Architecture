package com.app.cmpproject.domain.usecase

import com.app.cmpproject.data.model.AllUsersResponse
import com.app.cmpproject.data.remote.NetworkResult
import com.app.cmpproject.domain.repository.AllUsersRepository
import kotlinx.coroutines.flow.Flow

class AllUsersUseCase(private val allUsersRepository: AllUsersRepository) {
    suspend operator fun invoke(): Flow<NetworkResult<AllUsersResponse>> {
        return allUsersRepository.allUsers()
    }
}