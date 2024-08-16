package com.app.cmpproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllUsersResponse(
    @SerialName("users")
    var users: ArrayList<UserData> = arrayListOf(),
    @SerialName("total")
    var total: Int? = null,
    @SerialName("skip")
    var skip: Int? = null,
    @SerialName("limit")
    var limit: Int? = null
)