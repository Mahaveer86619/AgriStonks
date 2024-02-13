package com.se7en.agristonks.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.se7en.agristonks.data.preferences.SharedPreferences
import com.se7en.agristonks.uility.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screens.OnBoardingScreen.route)
    val startDestination = _startDestination

    init {
        viewModelScope.launch {
            _isLoading.value = true

//            _startDestination.value = Screens.TestScreen.route
//            _startDestination.value = Screens.HomeScreen.route
            if (SharedPreferences.isOnboardingCompleted()) {
                _startDestination.value = Screens.HomeScreen.route
            } else {
                _startDestination.value = Screens.OnBoardingScreen.route
            }
            _isLoading.value = false
        }
    }

}