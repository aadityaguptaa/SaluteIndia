package com.army.saluteindia.data.networklogin

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST("logout")
    suspend fun logout(): ResponseBody
}