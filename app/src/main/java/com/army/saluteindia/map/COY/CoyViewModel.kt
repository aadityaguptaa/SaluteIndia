package com.army.saluteindia.map.COY

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.repository.CoyRepository
import com.army.saluteindia.data.repository.HomeRepository
import com.army.saluteindia.data2.database
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.network.coys.CoyData
import com.army.saluteindia.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class CoyViewModel(private val repository: CoyRepository): BaseViewModel(repository) {

    val _coysComplete = MutableLiveData<Resource<CoyData>>()
    val coysComplete: LiveData<Resource<CoyData>>
        get() = _coysComplete

    fun getCoys(
    ) = viewModelScope.launch {
        _coysComplete.value = Resource.loading
        _coysComplete.value = repository.getCoys()
    }
}