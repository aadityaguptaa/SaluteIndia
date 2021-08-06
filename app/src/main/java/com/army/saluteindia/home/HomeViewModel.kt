package com.army.saluteindia.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.networklogin.responses.LoginResponse
import com.army.saluteindia.data.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: UserRepository): ViewModel(){

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
    get() = _user

    fun getUsers() = viewModelScope.launch {
        _user.value = Resource.loading
        _user.value = repository.getUser()
    }
}