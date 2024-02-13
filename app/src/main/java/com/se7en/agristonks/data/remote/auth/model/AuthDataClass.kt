package com.se7en.agristonks.data.remote.auth

import com.se7en.agristonks.data.remote.auth.models.AuthDataModel

data class SignInRequest(val email: String, val password: String)
data class SignUpRequest(val username: String, val email: String, val password: String, val role: String)
data class AuthDataModel(val email: String, val id: Int, val token: String, val username: String)
