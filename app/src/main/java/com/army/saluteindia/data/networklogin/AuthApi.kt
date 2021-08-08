package com.army.saluteindia.data.networklogin

import com.army.saluteindia.data.networklogin.responses.LoginResponse
import com.army.saluteindia.data.networklogin.responses.authInfo
import retrofit2.http.*

interface AuthApi {

    @POST("/token")
    suspend fun login(
        @Body authInfo: authInfo
    ): LoginResponse

}