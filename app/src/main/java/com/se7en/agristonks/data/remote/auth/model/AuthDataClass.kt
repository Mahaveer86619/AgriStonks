package com.se7en.agristonks.data.remote.auth.model

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
data class SignUpRequest(
    @SerializedName("uname")
    val uname: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("role")
    val role: String
)

data class AuthDataModel(
    val email: String,
    val id: Int,
    val token: String,
    val uname: String
)
data class Test(val message: String)
