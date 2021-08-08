package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.data.networklogin.AuthApi
import com.army.saluteindia.data.networklogin.UserApi
import com.army.saluteindia.data.networklogin.responses.authInfo
import com.army.saluteindia.network.ApiService

class HomeRepository(
    private val api: ApiService,
    private val preferences: UserPreferences
) : BaseRepository(){


    suspend fun getVillages() = safeApiCall { api.getVillagesList() }
    suspend fun getCoys() = safeApiCall { api.getCoysFromAPI() }
    suspend fun getMohallas() = safeApiCall { api.getMohallasList() }
    suspend fun getHouses() = safeApiCall { api.getHousesList() }



}