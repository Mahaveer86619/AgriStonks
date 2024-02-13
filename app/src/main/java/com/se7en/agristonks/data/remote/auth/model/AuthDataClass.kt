package com.se7en.agristonks.data.remote.auth.model


data class SignInRequest(
    val email: String,
    val password: String
)
data class SignUpRequest(
    val uname: String,
    val email: String,
    val password: String,
    val role: String
)

data class AuthDataModel(
    val email: String,
    val id: Int,
    val token: String,
    val uname: String
)
data class Test(val message: String)
