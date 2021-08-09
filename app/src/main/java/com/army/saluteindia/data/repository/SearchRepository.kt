package com.army.saluteindia.data.repository

import com.army.saluteindia.data.UserPreferences
import com.army.saluteindia.data.networklogin.responses.authInfo
import com.army.saluteindia.data.networklogin.responses.searchInfo
import com.army.saluteindia.network.ApiService

class SearchRepository(
    private val api: ApiService,
    private val preferences: UserPreferences
): BaseRepository() {

    suspend fun getPersons(
        searchInfo: searchInfo
    ) = safeApiCall {
        api.getPersons(searchInfo)
    }

}