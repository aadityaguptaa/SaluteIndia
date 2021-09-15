package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.network.ApiService

class HouseRepository(
    private val api: ApiService,
    private val preferences: UserPreferences
): BaseRepository() {

    suspend fun getHouses(mohallaName: String) =  safeApiCall { api.getHousesFromAPIWithMohallaFilter(mohallaName)}

}