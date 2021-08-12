package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.network.ApiService

class AddHouseRepository(
    private val api: ApiService,
    private val preferences: UserPreferences
) : BaseRepository(){

    suspend fun addHouse() = safeApiCall {  }


}