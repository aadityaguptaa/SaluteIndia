package com.army.saluteindia.data.repository

import com.army.saluteindia.data.networklogin.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

open class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {

        return withContext(Dispatchers.IO){
            try{
                Resource.success(apiCall.invoke())
            }catch(throwable: Throwable){
                when(throwable){
                    is HttpException -> {
                        Resource.failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.failure(true, null, null)
                    }
                }
            }
        }
    }
}
