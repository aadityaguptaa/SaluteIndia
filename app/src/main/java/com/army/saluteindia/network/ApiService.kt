package com.army.saluteindia.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

private const val BASE_URL = "https://72994389a3a9.ngrok.io/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

public interface ApiService{

    @Headers(value=["Auth: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/coy")
    fun getHousesFromAPI():
            Deferred<Coy>
/*
    Call<Houses> getHousesFromAPI(@Header("Auth:") token: String)
*/

}

object RestApi{
    val RETROFIT_SERVICE: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}