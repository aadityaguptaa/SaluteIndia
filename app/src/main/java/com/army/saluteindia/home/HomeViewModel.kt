package com.army.saluteindia.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.networklogin.responses.LoginResponse
import com.army.saluteindia.data.repository.HomeRepository
import com.army.saluteindia.data.repository.UserRepository
import com.army.saluteindia.network.coys.CoyData
import com.army.saluteindia.network.houseTemp.HouseDataTemp
import com.army.saluteindia.network.houses.HouseData
import com.army.saluteindia.network.mohallas.MohallaData
import com.army.saluteindia.network.villages.Data
import com.army.saluteindia.network.villages.VillageData
import com.army.saluteindia.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: HomeRepository): BaseViewModel(repository){

    val _villagesComplete = MutableLiveData<Resource<VillageData>>()
    val villagesComplete: LiveData<Resource<VillageData>>
        get() = _villagesComplete

    val _mohallasComplete = MutableLiveData<Resource<MohallaData>>()
    val mohallasComplete: LiveData<Resource<MohallaData>>
        get() = _mohallasComplete

    val _housesComplete = MutableLiveData<Resource<HouseDataTemp>>()
    val housesComplete: LiveData<Resource<HouseDataTemp>>
        get() = _housesComplete

    val _coysComplete = MutableLiveData<Resource<CoyData>>()
    val coysComplete: LiveData<Resource<CoyData>>
        get() = _coysComplete

    fun getVillages(
    ) = viewModelScope.launch {
        _villagesComplete.value = Resource.loading
        _villagesComplete.value = repository.getVillages()
    }

    fun getHouses(
    ) = viewModelScope.launch {
        _housesComplete.value = Resource.loading
        _housesComplete.value = repository.getHouses()
    }

    fun getCoys(
    ) = viewModelScope.launch {
        _coysComplete.value = Resource.loading
        _coysComplete.value = repository.getCoys()
    }
    fun getMohallas(
    ) = viewModelScope.launch {
        _mohallasComplete.value = Resource.loading
        _mohallasComplete.value = repository.getMohallas()
    }


}