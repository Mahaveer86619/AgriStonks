package com.se7en.agristonks.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.se7en.agristonks.data.preferences.DataStorePreference
import com.se7en.agristonks.data.preferences.SharedPreferences
import com.se7en.agristonks.data.remote.auth.authservice.AuthService
import com.se7en.agristonks.data.remote.auth.model.AuthDataModel
import com.se7en.agristonks.data.remote.auth.model.SignInRequest
import com.se7en.agristonks.data.remote.auth.model.SignUpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authService: AuthService,
    private val dataStorePref: DataStorePreference
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
        return true
    }

    fun validateSignUpForm(): Boolean {
        return true
    }

    fun loadDataForSignIn(
        email: String,
        password: String
    ) {

        _emailState.value = email
        _passwordState.value = password

    }

    fun loadDataForSignUp(
        email: String,
        username: String,
        password: String,
        rePassword: String,
        role: String
    ) {

        _emailState.value = email
        _usernameState.value = username
        _passwordState.value = password
        _rePasswordState.value = rePassword
        _roleState.value = role

    }

    fun signIn() {
        if (validateSignInForm()) {
            viewModelScope.launch {
                _isSigningIn.value = true
                _error.value = ""
                try {

                    val response = authService.signIn(SignInRequest(emailState.value, passwordState.value))

                    if (response.isSuccessful) {
                        val userInResponse: AuthDataModel? = response.body()

                        Log.d(
                            "DevTime",
                            "Signed In, $userInResponse"
                        )

                        if (userInResponse != null) {
                            // Storing user data securely
                            dataStorePref.saveUserDetails(
                                AuthDataModel(
                                    email = userInResponse.email,
                                    token = userInResponse.token,
                                    uname = userInResponse.uname,
                                    id = userInResponse.id
                                )
                            )
                            SharedPreferences.setTokenKey(userInResponse.token)
                        } else {
                            _error.value = "User data missing in response"
                            return@launch
                        }
                    } else {
                        handleNetworkError(response.code())
                    }
                } catch (e: Exception) {
                    _error.value = e.message.toString()
                    return@launch
                } finally {
                    _isSigningIn.value = false
                }
            }
        } else {
            _error.value = "Passwords Don't match"
        }
    }

    fun signUp() {
        if (validateSignUpForm()) {
            viewModelScope.launch {
                _isSigningUp.value = true
                _error.value = ""
                try {

                    val response = authService.signUp(
                        SignUpRequest(
                            uname = usernameState.value,
                            email = emailState.value,
                            password = passwordState.value,
                            role = roleState.value
                        )
                    )

                    if (response.isSuccessful) {
                        val userInResponse = response.body()

                        Log.d(
                            "DevTime",
                            "Signed up, $userInResponse"
                        )

                        if (userInResponse != null) {
                            dataStorePref.saveUserDetails(
                                AuthDataModel(
                                    email = userInResponse.email,
                                    token = userInResponse.token,
                                    uname = userInResponse.uname,
                                    id = userInResponse.id
                                )
                            )
                            SharedPreferences.setTokenKey(userInResponse.token)
                        } else {
                            _error.value = "User data missing in response"
                            Log.d(
                                "DevTime",
                                "error, ${_error.value}"
                            )
                            return@launch
                        }
                    } else {
                        handleNetworkError(response.code())
                    }
                } catch (e: Exception) {
                    _error.value = e.message.toString()
                    Log.d(
                        "DevTime",
                        "error, ${_error.value}"
                    )
                    return@launch
                } finally {
                    _isSigningUp.value = false
                }
            }
        } else {
            _error.value = "Passwords Don't match"
            Log.d(
                "DevTime",
                "error, ${_error.value}"
            )
        }
    }

    private fun handleNetworkError(
        code: Int
    ) {

        when(code) {
            400 -> {
                _error.value = "Bad Request (code: $code): Please check your input.}"
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            401 -> {
                _error.value = "Unauthorized (code: $code): Invalid credentials"
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            403 -> {
                _error.value = "Forbidden (code: $code): You don't have permission to access this resource."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            404 -> {
                _error.value = "Not Found (code: $code): The requested resource could not be found."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            409 -> {
                _error.value = "Conflict (code: $code): The request cannot be completed due to a conflict."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            422 -> {
                _error.value = "Unprocessable Entity (code: $code): The request data was invalid."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            429 -> {
                _error.value = "Too Many Requests (code: $code): You've exceeded the rate limit. Please try again later."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            500 -> {
                _error.value = "Internal Server Error (code: $code): An unexpected server-side error occurred. Please try again later."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            502 -> {
                _error.value = "Bad Gateway (code: $code): The server received an invalid response from another server."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            503 -> {
                _error.value = "Service Unavailable (code: $code): The server is currently unavailable. Please try again later."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
            else -> {
                _error.value = "Unknown Error: An unexpected error occurred (code: $code). Please try again later."
                Log.d(
                    "DevTime",
                    "error, ${_error.value}"
                )
                return
            }
        }

    }

}