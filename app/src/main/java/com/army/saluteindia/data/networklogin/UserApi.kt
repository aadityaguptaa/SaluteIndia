package com.army.saluteindia.data.networklogin

import com.army.saluteindia.data.networklogin.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse
}