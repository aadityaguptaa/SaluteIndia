package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.network.ApiService

class CoyRepository(
    private val api: ApiService,
    private val preferences: UserPreferences
) : BaseRepository(){

    suspend fun getCoys() = safeApiCall { api.getCoysFromAPI() }


}