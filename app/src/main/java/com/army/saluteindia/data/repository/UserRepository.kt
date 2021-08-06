package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.data.networklogin.AuthApi
import com.army.saluteindia.data.networklogin.UserApi

class UserRepository(
    private val api: UserApi
    ) : BaseRepository(){

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}