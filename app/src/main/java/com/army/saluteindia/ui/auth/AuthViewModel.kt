package com.army.saluteindia.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.networklogin.responses.LoginResponse
import com.army.saluteindia.data.repository.AuthRepository
import com.army.saluteindia.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
): BaseViewModel(repository){

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()

    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.loading
        _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAuthToken(token: String) {
        repository.saveAuthToken(token)
    }
}