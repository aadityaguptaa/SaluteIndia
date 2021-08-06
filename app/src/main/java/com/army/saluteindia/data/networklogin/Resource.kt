package com.army.saluteindia.data.networklogin

import okhttp3.ResponseBody

sealed class Resource<out T> {

    data class success<out T>(val value: T) : Resource<T>()

    data class failure(
        val isNetworking: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ): Resource<Nothing>()

    object loading: Resource<Nothing>()
}