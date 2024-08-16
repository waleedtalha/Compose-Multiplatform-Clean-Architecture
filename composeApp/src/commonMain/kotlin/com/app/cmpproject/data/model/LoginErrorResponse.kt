package com.app.cmpproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginErrorResponse (
    @SerialName("message" )
    var message : String? = null
)