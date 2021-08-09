package com.army.saluteindia.data.repository

import androidx.core.content.ContentProviderCompat.requireContext
import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.data2.database
import com.army.saluteindia.network.ApiService

class VillageRepository(
private val api: ApiService,
private val preferences: UserPreferences
): BaseRepository() {


    suspend fun getVillages(coyName: String) = safeApiCall { api.getVillagesFromAPIWithCompanyFilter(coyName) }

    suspend fun getVillages() = safeApiCall { api.getVillagesList() }



}