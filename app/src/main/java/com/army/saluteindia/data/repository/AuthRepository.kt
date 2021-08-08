package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.data.networklogin.AuthApi
import com.army.saluteindia.data.networklogin.responses.authInfo

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
    ) : BaseRepository(){

    suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(authInfo(username, password))
    }

    suspend fun saveAuthToken(token: String){
        preferences.saveAuthToken(token)
    }
}