package com.army.saluteindia.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.army.saluteindia.data.repository.AuthRepository
import com.army.saluteindia.data.repository.BaseRepository
import com.army.saluteindia.data.repository.UserRepository
import com.army.saluteindia.home.HomeViewModel
import com.army.saluteindia.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (
    private val repository: BaseRepository,
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T

            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}