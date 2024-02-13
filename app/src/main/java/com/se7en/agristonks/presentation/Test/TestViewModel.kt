package com.se7en.agristonks.presentation.Test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.se7en.agristonks.data.remote.auth.authservice.AuthService
import com.se7en.agristonks.data.remote.auth.model.AuthDataModel
import com.se7en.agristonks.data.remote.auth.model.SignInRequest
import com.se7en.agristonks.data.remote.auth.model.SignUpRequest
import com.se7en.agristonks.data.remote.auth.model.Test
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val authService: AuthService,
) : ViewModel() {

    private val _messageState = MutableStateFlow("")
    val messageState = _messageState.value

    fun testHit() {
        viewModelScope.launch {
            try {
//                val response =
//                    authService.signUp(
//                        SignUpRequest(
//                            uname = "warehouse",
//                            email = "warehouse@gmail.com",
//                            password = "warehouse",
//                            role = "ROLE_WAREHOUSE"
//                        )
//                    )
                val response = authService.test()

                val data = response.body()

                Log.d(
                    "DevTime",
                    "data = $data"
                )

//                if (response.isSuccessful) {
//                    val data: String? = response.body()
//                    if (data != null) {
//                        Log.d(
//                            "DevTime",
//                            "data = $data"
//                        )
//                    }
//
//                } else {
//                    Log.d(
//                        "DevTime",
//                        response.code().toString()
//                    )
//                }
            } catch (e: Exception) {
                Log.d(
                    "DevTime",
                    "error: ${e.message}"
                )
            }
        }
    }

}