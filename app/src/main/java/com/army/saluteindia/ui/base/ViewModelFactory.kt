package com.army.saluteindia.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.army.saluteindia.data.repository.*
import com.army.saluteindia.home.HomeViewModel
import com.army.saluteindia.home.addHouse.AddHouseViewModel
import com.army.saluteindia.home.search.SearchViewModel
import com.army.saluteindia.map.COY.CoyViewModel
import com.army.saluteindia.map.House.HouseViewModel
import com.army.saluteindia.map.Mohalla.MohallaViewModel
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
            modelClass.isAssignableFrom(MohallaViewModel::class.java) -> MohallaViewModel(repository as MohallaRepository) as T
            modelClass.isAssignableFrom(HouseViewModel::class.java) -> HouseViewModel(repository as HouseRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(repository as SearchRepository) as T
            modelClass.isAssignableFrom(AddHouseViewModel::class.java) -> AddHouseViewModel(repository as AddHouseRepository) as T


            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }
}