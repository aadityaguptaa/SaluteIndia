package com.army.saluteindia.data

import android.content.Context
import android.provider.Contacts.SettingsColumns.KEY
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences(
    context: Context
) {

    private val applicationContext = context.applicationContext
    private val datastore: DataStore<Preferences>

    init{
        datastore = applicationContext.createDataStore(
            name = "my_name_store"
        )
    }

    val authToken: Flow<String?>
        get() = datastore.data.map { preferences ->
            preferences[KEY_AUTH]
        }

    suspend fun saveAuthToken(authToken: String){
        datastore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun clear(){
        datastore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object{
        private val KEY_AUTH = preferencesKey<String>("key_auth")
    }
}