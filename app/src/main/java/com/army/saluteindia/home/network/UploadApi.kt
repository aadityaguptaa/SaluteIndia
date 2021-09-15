package com.army.saluteindia.home.network

import androidx.databinding.ktx.BuildConfig
import com.army.saluteindia.data.networklogin.RemoteDataSource
import com.army.saluteindia.home.network.response.UploadResponse
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.util.concurrent.TimeUnit

interface UploadApi {


    @Multipart
    @POST("apicall=upload")
    fun uploadImage(
        @Part doc: MultipartBody.Part,
        @Part("desc") desc: RequestBody
    ): Call<UploadResponse>


    fun<Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api{
        return Retrofit.Builder()
            .baseUrl(RemoteDataSource.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization", "Bearer $authToken" )
                            it.addHeader("Content-Type", "application/json")
                        }.build())
                    }.also { client ->
                        if(com.army.saluteindia.BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}
