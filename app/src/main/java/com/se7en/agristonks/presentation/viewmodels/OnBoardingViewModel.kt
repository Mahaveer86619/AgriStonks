package com.se7en.agristonks.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.se7en.agristonks.data.preferences.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(

): ViewModel() {

    fun saveOnBoardingState(isCompleted: Boolean) {
        SharedPreferences.setOnboardingCompleted(isCompleted = isCompleted)
    }

}