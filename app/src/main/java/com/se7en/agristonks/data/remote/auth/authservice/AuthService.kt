package com.se7en.agristonks.data.remote.auth.authservice

import com.se7en.agristonks.data.remote.auth.model.AuthDataModel
import com.se7en.agristonks.data.remote.auth.model.SignInRequest
import com.se7en.agristonks.data.remote.auth.model.SignUpRequest
import com.se7en.agristonks.data.remote.auth.model.Test
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("/signin")
    suspend fun signIn(@Body credentials: SignInRequest): Response<AuthDataModel>

    @POST("/signup")
    suspend fun signUp(@Body userDetails: SignUpRequest): Response<AuthDataModel>

    @GET("test/anon")
    suspend fun test(): Response<String>
}