package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.network.ApiService

class VillageRepository(
private val api: ApiService,
private val preferences: UserPreferences
): BaseRepository() {

    suspend fun getVillages(coyName: String) = safeApiCall { api.getVillagesFromAPIWithCompanyFilter(coyName) }

}