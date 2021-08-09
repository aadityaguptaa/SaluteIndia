package com.army.saluteindia.map.House

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.HouseRepository
import com.army.saluteindia.data.repository.MohallaRepository
import com.army.saluteindia.network.houses.HouseData
import com.army.saluteindia.network.mohallas.MohallaData
import com.army.saluteindia.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HouseViewModel(private val repository: HouseRepository): BaseViewModel(repository) {

    val _housesComplete = MutableLiveData<Resource<HouseData>>()
    val housesComplete: LiveData<Resource<HouseData>>
        get() = _housesComplete

    fun getHouses(mohallaName: String
    ) = viewModelScope.launch {
        _housesComplete.value = Resource.loading
        _housesComplete.value = repository.getHouses(mohallaName)
    }
}