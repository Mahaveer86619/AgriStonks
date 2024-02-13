package com.se7en.agristonks.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.se7en.agristonks.data.remote.auth.authservice.AuthService
import com.se7en.agristonks.data.remote.auth.model.SignInRequest
import com.se7en.agristonks.data.remote.auth.model.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    private val _emailState = MutableStateFlow("")
    val emailState: StateFlow<String> = _emailState.asStateFlow()

    private val _passwordState = MutableStateFlow("")
    val passwordState: StateFlow<String> = _passwordState.asStateFlow()

    private val _rePasswordState = MutableStateFlow("")
    val rePasswordState: StateFlow<String> = _rePasswordState.asStateFlow()

    private val _usernameState = MutableStateFlow("")
    val usernameState: StateFlow<String> = _usernameState.asStateFlow()

    private val _roleState = MutableStateFlow("")
    val roleState: StateFlow<String> = _roleState.asStateFlow()

    private val _isSigningUp = MutableStateFlow(false)
    val isSigningUp: StateFlow<Boolean> = _isSigningUp.asStateFlow()

    private val _isSigningIn = MutableStateFlow(false)
    val isSigningIn: StateFlow<Boolean> = _isSigningIn.asStateFlow()

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()


    fun validateSignInForm(): Boolean {
        // Implement validation rules for email and password
        return true // Return true if valid, false otherwise
    }

    fun validateSignUpForm(): Boolean {
        // Implement validation rules for all sign-up fields
        return true // Return true if valid, false otherwise
    }

    fun loadData(
        
    ) {

    }

    fun signIn() {
        if (validateSignInForm()) {
            viewModelScope.launch {
                _isSigningIn.value = true
                try {
                    val response =
                        authService.signIn(SignInRequest(emailState.value, passwordState.value))
                    if (response.isSuccessful) {
                        val user = response.body() // Assuming a User data class exists
                        if (user != null) {
                            // Store user data securely (e.g., using SharedPreferences)
                            // navigateToHomeScreen()
                        } else {
                            // Handle case where user data is missing in response
                        }
                    } else {
                        when (response.code()) {
                            401 -> showMessage("Invalid credentials")
                            // Handle other specific error codes with appropriate messages
                            else -> showErrorMessage("Oops, something went wrong!")
                        }
                    }
                } catch (e: Exception) {
                    handleNetworkError(e) // Implement network error handling
                } finally {
                    _isSigningIn.value = false
                }
            }
        }
    }

    fun signUp() {
        if (validateSignInForm()) {
            viewModelScope.launch {
                _isSigningIn.value = true
                try {
                    val response = authService.signUp(
                        SignUpRequest(
                            usernameState.value,
                            emailState.value,
                            passwordState.value,
                            roleState.value
                        )
                    )
                    if (response.isSuccessful) {
                        val user = response.body() // Assuming a User data class exists
                        if (user != null) {
                            // Store user data securely (e.g., using SharedPreferences)
                            // navigateToHomeScreen()
                        } else {
                            // Handle case where user data is missing in response
                        }
                    } else {
                        when (response.code()) {
                            401 -> showMessage("Invalid credentials")
                            // Handle other specific error codes with appropriate messages
                            else -> showErrorMessage("Oops, something went wrong!")
                        }
                    }
                } catch (e: Exception) {
                    handleNetworkError(e) // Implement network error handling
                } finally {
                    _isSigningIn.value = false
                }
            }
        }
    }


}