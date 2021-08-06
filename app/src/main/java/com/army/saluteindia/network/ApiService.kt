package com.army.saluteindia.network

import com.army.saluteindia.network.coys.CoyData
import com.army.saluteindia.network.houses.HouseData
import com.army.saluteindia.network.mohallas.MohallaData
import com.army.saluteindia.network.villages.VillageData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://armyproj.herokuapp.com/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

var httpInterceptor = HttpLoggingInterceptor()
var okHttpClient = OkHttpClient.Builder()
    .addInterceptor(httpInterceptor)
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

public interface ApiService{

    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/coy")
    fun getCoysFromAPI():
            Deferred<CoyData>

    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/village")
    fun getVillagesFromAPIWithCompanyFilter(@Query("coyName") coyname: String):
            Deferred<VillageData>

    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/mohalla")
    fun getMohallasFromAPIWithVillageFilter(@Query("villageName") villagename: String):
            Deferred<MohallaData>

    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/house")
    fun getHousesFromAPIWithMohallaFilter(@Query("mohallaName") mohallaname: String):
            Deferred<HouseData>

    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/village")
    fun getVillagesList():
            Deferred<VillageData>

    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/mohalla")
    fun getMohallasList():
            Deferred<MohallaData>

    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/house")
    fun getHousesList():
            Deferred<HouseData>

    /*@Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/coy?coyName={coy}&type=villages")
    fun getVillagesFromAPI(@Path("coy") coy: String):
            Deferred<data>*/

    @Multipart
    @POST("")
    fun uploadImage(
        @Part document: MultipartBody.Part,
        @Part("desc") desc: RequestBody
    )

    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @POST("/house")
    fun createHouse(@Body house: NewHouse ): Deferred<NewHouse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") email: String,
        @Field("password") password: String,
    ) : Any
}

object RestApi{
    val RETROFIT_SERVICE: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}