package com.army.saluteindia.network

import android.app.PendingIntent.getActivity
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.datastore.preferences.preferencesKey
import com.army.saluteindia.BuildConfig
import com.army.saluteindia.data.UserPreferences
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
import java.util.concurrent.TimeUnit
import android.content.SharedPreferences
import com.army.saluteindia.ui.auth.LoginFragment


private const val BASE_URL = "https://armyproj.herokuapp.com/"


/*private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Authorization", "Bearer $authToken" )
                }.build())
            }.also { client ->
                if(BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    )
    .build()*/

public interface ApiService{

    @GET("/coy")
    suspend fun getCoysFromAPI(): CoyData


    @GET("/village")
    suspend fun getVillagesFromAPIWithCompanyFilter(@Query("coyName") coyname: String): VillageData

    @GET("/mohalla")
    suspend fun getMohallasFromAPIWithVillageFilter(@Query("villageName") villagename: String): MohallaData


    @Headers(value=["Authorization: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI2MGYxYjdkMTBhNjFlZWQzNDI5NjI5OWQiLCJ1c2VybmFtZSI6ImlyZXNoYXJtYSIsImJ0biI6Ijc4IEJOIiwiY3JlYXRlZEF0IjoxNjI2NDUzOTY5LjYyMDIyMiwiZXhwIjoxNjI3NjY1MjQ2fQ.Q4nA7hfMiP1Zm2aFQ6P_NTTESeVkP-YqyWk1OKtldLw"])
    @GET("/house")
    fun getHousesFromAPIWithMohallaFilter(@Query("mohallaName") mohallaname: String):
            Deferred<HouseData>

    @GET("/village")
    suspend fun getVillagesList(): VillageData

    @GET("/mohalla")
    suspend fun getMohallasList(): MohallaData

    @GET("/house")
    suspend fun getHousesList(): HouseData

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

/*object RestApi{
    val RETROFIT_SERVICE: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}*/
