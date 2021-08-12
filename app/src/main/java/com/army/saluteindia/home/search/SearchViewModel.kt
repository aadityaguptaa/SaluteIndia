package com.army.saluteindia.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data.networklogin.Resource
import com.army.saluteindia.data.networklogin.responses.searchInfo
import com.army.saluteindia.data.networklogin.searchResponses.searchResponse
import com.army.saluteindia.data.repository.SearchRepository
import com.army.saluteindia.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository): BaseViewModel(repository) {

    val _personsComplete = MutableLiveData<Resource<searchResponse>>()
    val personsComplete: LiveData<Resource<searchResponse>>
        get() = _personsComplete

    fun getPersons(searchInfo: searchInfo
    ) = viewModelScope.launch {
        _personsComplete.value = Resource.loading
        _personsComplete.value = repository.getPersons(searchInfo)
    }
}