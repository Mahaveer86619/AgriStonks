package com.se7en.agristonks.data.remote.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/signin")
    suspend fun signIn(@Body credentials: SignInRequest): Response<AuthDataModel>

    @POST("/signup")
    suspend fun signUp(@Body userDetails: SignUpRequest): Response<AuthDataModel>
}