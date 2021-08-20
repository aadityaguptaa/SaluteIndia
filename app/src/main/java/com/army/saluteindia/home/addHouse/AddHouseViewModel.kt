package com.army.saluteindia.home.addHouse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.AddHouseRepository
import com.army.saluteindia.data.repository.VillageRepository
import com.army.saluteindia.home.addHouse.jsonformat.newhousejson
import com.army.saluteindia.network.villages.VillageData
import com.army.saluteindia.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AddHouseViewModel (private val repository: AddHouseRepository): BaseViewModel(repository) {


    fun addHouse(
        newHouse: newhousejson
    ) = viewModelScope.launch {
        repository.addHouse(newHouse)
    }





}