package com.army.saluteindia.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.army.saluteindia.data.repository.*
import com.army.saluteindia.home.HomeViewModel
import com.army.saluteindia.map.COY.CoyViewModel
import com.army.saluteindia.map.Village.VillageViewModel
import com.army.saluteindia.ui.auth.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory (
    private val repository: BaseRepository,
): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as HomeRepository) as T
            modelClass.isAssignableFrom(CoyViewModel::class.java) -> CoyViewModel(repository as CoyRepository) as T
            modelClass.isAssignableFrom(VillageViewModel::class.java) -> VillageViewModel(repository as VillageRepository) as T


            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}