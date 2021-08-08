package com.army.saluteindia.map.Mohalla

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.MohallaRepository
import com.army.saluteindia.data.repository.VillageRepository
import com.army.saluteindia.network.mohallas.MohallaData
import com.army.saluteindia.network.villages.VillageData
import com.army.saluteindia.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MohallaViewModel(private val repository: MohallaRepository): BaseViewModel(repository) {

    val _mohallasComplete = MutableLiveData<Resource<MohallaData>>()
    val mohallasComplete: LiveData<Resource<MohallaData>>
        get() = _mohallasComplete

    fun getMohallas(villageName: String
    ) = viewModelScope.launch {
        _mohallasComplete.value = Resource.loading
        _mohallasComplete.value = repository.getMohallas(villageName)
    }
}