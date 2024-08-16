package com.app.cmpproject.data.remote

import com.app.cmpproject.data.model.AllUsersResponse
import com.app.cmpproject.data.model.LoginErrorResponse
import com.app.cmpproject.data.model.LoginResponse
import com.app.cmpproject.util.extensions.*
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headers

class RemoteDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteDataSource() {

    override suspend fun login(
        username: String,
        password: String
    ): NetworkResult<LoginResponse> {
        val requestBody = """
            {
                "username": "$username",
                "password": "$password"
            }
        """.trimIndent()
        return try {
            val response = httpClient.post(LOGIN_END_POINT) {
                contentType(ContentType.Application.Json)
                setBody(requestBody)
            }

            if (response.status == HttpStatusCode.OK) {
                val responseBody = response.body<LoginResponse>()
                NetworkResult.Success(responseBody)
            } else {
                val errorResponse = response.body<LoginErrorResponse>()
                NetworkResult.Error(null, errorResponse.message?:"")
            }
        } catch (e: Exception) {
            NetworkResult.Error(null, e.toString())
        }
    }

    override suspend fun allUsers(): NetworkResult<AllUsersResponse> {
        return try {
            val response = httpClient.get(USERS_END_POINT) {
                headers {
                    append(HttpHeaders.Authorization, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3MTgzNjQwNjcsImV4cCI6MTcxODM2NzY2N30.qE28ig8Sh_5fFzadrWQM77vUYOxPZ8gSFD0cX5-7iMg")
                }
            }

            if (response.status == HttpStatusCode.OK) {
                val responseBody = response.body<AllUsersResponse>()
                NetworkResult.Success(responseBody)
            } else {
                val errorResponse = response.body<LoginErrorResponse>()
                NetworkResult.Error(null, errorResponse.message ?: "Unknown error occurred")
            }
        } catch (e: Exception) {
            NetworkResult.Error(null, e.toString())
        }
    }
}