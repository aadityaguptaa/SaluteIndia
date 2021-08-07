package com.army.saluteindia.ui.base

import androidx.lifecycle.ViewModel
import com.army.saluteindia.data.networklogin.UserApi
import com.army.saluteindia.data.networklogin.responses.User
import com.army.saluteindia.data.repository.BaseRepository

abstract class BaseViewModel (
    private val repository: BaseRepository
): ViewModel(){

    suspend fun logout(api: UserApi) = repository.logout(api)
}