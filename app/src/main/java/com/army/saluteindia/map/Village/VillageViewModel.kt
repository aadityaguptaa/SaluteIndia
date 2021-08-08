package com.army.saluteindia.map.Village

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.CoyRepository
import com.army.saluteindia.data.repository.VillageRepository
import com.army.saluteindia.network.coys.CoyData
import com.army.saluteindia.network.villages.VillageData
import com.army.saluteindia.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class VillageViewModel (private val repository: VillageRepository): BaseViewModel(repository){

    val _villagesComplete = MutableLiveData<Resource<VillageData>>()
    val villagesComplete: LiveData<Resource<VillageData>>
        get() = _villagesComplete

    fun getVillages(coyName: String
    ) = viewModelScope.launch {
        _villagesComplete.value = Resource.loading
        _villagesComplete.value = repository.getVillages(coyName)
    }
}