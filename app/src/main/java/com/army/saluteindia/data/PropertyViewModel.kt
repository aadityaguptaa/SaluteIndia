package com.army.saluteindia.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PropertyViewModel(application: Application) : AndroidViewModel(application){

    lateinit var propertyList: LiveData<List<Property>>
    lateinit var coyList: LiveData<List<String>>
/*
    lateinit var villageCountList: MutableLiveData<MutableList<Int>>
*/  lateinit var villages: LiveData<List<String>>
    lateinit var mohallas: LiveData<List<String>>
    var villagesDone: MutableLiveData<Int> = MutableLiveData(0)


    private lateinit var repository: PropertyRepository

    init{
        var propertyDao: PropertyDao = PropertyDatabase.getDatabase(application).propertyDao()
        repository = PropertyRepository(propertyDao)
        propertyList = repository.propertyList
        coyList = repository.coyList
        villages = repository.villages
        mohallas = repository.mohallas

/*
        villageCountList = repository.villageCount
*/

    }

    fun addProperty(property: Property){
        viewModelScope.launch(Dispatchers.IO){
            repository.addProperty(property)
        }
    }

    fun updateProperty(property: Property){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateProperty(property)
        }
    }

    fun deleteProperty(property: Property){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteProperty(property)
        }
    }

    fun deleteAllProperty(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }

    fun getVillages(coy: String){
        viewModelScope.launch(Dispatchers.IO) {
            villages = repository.getVillages(coy)
            villagesDone.postValue(1)
        }
    }

    fun getMohallas(village: String){
        viewModelScope.launch(Dispatchers.IO) {
            mohallas = repository.getMohallas(village)
        }
    }




}