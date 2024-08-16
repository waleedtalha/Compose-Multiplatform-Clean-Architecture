package com.app.cmpproject.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class UserData(
    @SerialName("id") var id: Int? = null,
    @SerialName("firstName") var firstName: String? = null,
    @SerialName("lastName") var lastName: String? = null,
    @SerialName("maidenName") var maidenName: String? = null,
    @SerialName("age") var age: Int? = null,
    @SerialName("gender") var gender: String? = null,
    @SerialName("email") var email: String? = null,
    @SerialName("phone") var phone: String? = null,
    @SerialName("username") var username: String? = null,
    @SerialName("password") var password: String? = null,
    @SerialName("birthDate") var birthDate: String? = null,
    @SerialName("image") var image: String? = null,
    @SerialName("bloodGroup") var bloodGroup: String? = null,
    @SerialName("height") var height: Double? = null,
    @SerialName("weight") var weight: Double? = null,
    @SerialName("eyeColor") var eyeColor: String? = null,
    @SerialName("hair") var hair: JsonObject? = null,
    @SerialName("ip") var ip: String? = null,
    @SerialName("address") var address: JsonObject? = null,
    @SerialName("macAddress") var macAddress: String? = null,
    @SerialName("university") var university: String? = null,
    @SerialName("bank") var bank: JsonObject? = null,
    @SerialName("company") var company: JsonObject? = null,
    @SerialName("ein") var ein: String? = null,
    @SerialName("ssn") var ssn: String? = null,
    @SerialName("userAgent") var userAgent: String? = null,
    @SerialName("crypto") var crypto: JsonObject? = null,
    @SerialName("role") var role: String? = null
)
