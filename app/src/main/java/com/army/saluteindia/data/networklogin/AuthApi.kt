package com.army.saluteindia.data.networklogin

import com.army.saluteindia.data.networklogin.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field( "password") password: String
    ): LoginResponse

}