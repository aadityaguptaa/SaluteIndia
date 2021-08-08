package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.network.ApiService

class MohallaRepository(
    private val api: ApiService,
    private val preferences: UserPreferences
): BaseRepository() {

    suspend fun getMohallas(villageName: String) = safeApiCall { api.getMohallasFromAPIWithVillageFilter(villageName) }

}