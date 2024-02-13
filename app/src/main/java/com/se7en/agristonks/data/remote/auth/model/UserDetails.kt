package com.se7en.agristonks.data.remote

data class UserDetails(
    val uname: String,
    val email: String,
    val password: String,
    val role: String
)

data class UserPreferences(
    val onboardingCompleted: Boolean = false,
    val token: String? = null,
    val userDetails: UserDetails? = null
)
