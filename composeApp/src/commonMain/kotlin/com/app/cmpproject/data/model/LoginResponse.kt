package com.app.cmpproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("id")
    var id: Int? = null,
    @SerialName("username")
    var username: String? = null,
    @SerialName("email")
    var email: String? = null,
    @SerialName("firstName")
    var firstName: String? = null,
    @SerialName("lastName")
    var lastName: String? = null,
    @SerialName("gender")
    var gender: String? = null,
    @SerialName("image")
    var image: String? = null,
    @SerialName("token")
    var token: String? = null,
    @SerialName("refreshToken")
    var refreshToken: String? = null
)