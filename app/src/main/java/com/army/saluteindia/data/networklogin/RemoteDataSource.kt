package com.army.saluteindia.data.networklogin

import com.army.saluteindia.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.FieldNamingPolicy

import com.google.gson.GsonBuilder




class RemoteDataSource {

    companion object {
        const val BASE_URL = "https://armyproj.herokuapp.com/"
    }

    fun<Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api{

        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setLenient()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization", "Bearer $authToken" )
                            it.addHeader("Content-Type", "application/json")
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
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()
            .create(api)
    }
}