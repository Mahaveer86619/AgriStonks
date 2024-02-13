package com.se7en.agristonks.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.se7en.agristonks.data.remote.auth.model.AuthDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store_pref")
class DataStorePreference(context: Context) {

    object PreferencesKey {
        val emailKey = stringPreferencesKey(name = "user_email")
        val idKey = intPreferencesKey(name = "user_id")
        val tokenKey = stringPreferencesKey(name = "user_token")
        val usernameKey = stringPreferencesKey(name = "user_username")
    }

    private val dataStore = context.dataStore

    suspend fun saveUserDetails(user: AuthDataModel) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.emailKey] = user.email
            preferences[PreferencesKey.idKey] = user.id
            preferences[PreferencesKey.tokenKey] = user.token
            preferences[PreferencesKey.usernameKey] = user.uname
        }
    }

    fun readUserDetails(): Flow<AuthDataModel?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val email = preferences[PreferencesKey.emailKey] ?: return@map null
                val id = preferences[PreferencesKey.idKey] ?: return@map null
                val token = preferences[PreferencesKey.tokenKey] ?: return@map null
                val username = preferences[PreferencesKey.usernameKey] ?: return@map null
                AuthDataModel(email, id, token, username)
            }
    }

}
