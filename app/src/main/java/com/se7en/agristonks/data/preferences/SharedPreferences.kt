package com.se7en.agristonks.data.preferences

import android.content.Context
import com.se7en.agristonks.MyApplication

object SharedPreferences {

    private const val ONBOARDING_PREF = "onboarding_pref"
    private const val KEY_IS_ONBOARDED = "is_onboarded"
    private const val TOKEN = "token"

    private val preferences by lazy {
        MyApplication.appContext.applicationContext.getSharedPreferences(
            ONBOARDING_PREF,
            Context.MODE_PRIVATE
        )
    }

    private val tokenPreferences by lazy {
        MyApplication.appContext.applicationContext.getSharedPreferences(
            TOKEN,
            Context.MODE_PRIVATE
        )
    }

    fun isOnboardingCompleted(): Boolean {
        return preferences.getBoolean(KEY_IS_ONBOARDED, false)
    }

    fun setOnboardingCompleted(isCompleted: Boolean) {
        preferences.edit().putBoolean(KEY_IS_ONBOARDED, isCompleted).apply()
    }

    fun setTokenKey(token: String) {
        tokenPreferences.edit().putString(TOKEN, token).apply()
    }

    fun getTokenKey(): String? {
        return preferences.getString(TOKEN, "")
    }

//    private fun saveUserDetails(userDetails: UserDetails) {
//        val gson = Gson()
//        val json = gson.toJson(userDetails)
//        with(SharedPreferences.edit()) {
//            putString("userDetails", json)
//            apply()
//        }
//    }


}