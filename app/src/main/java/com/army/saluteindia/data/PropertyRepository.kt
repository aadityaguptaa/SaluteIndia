package com.army.saluteindia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PropertyRepository(private val propertyDao: PropertyDao) {

    val propertyList: LiveData<List<Property>> = propertyDao.getPropertyList()
    val coyList: LiveData<List<String>> = propertyDao.getCoyList()
    val villages: LiveData<List<String>> = propertyDao.getAllVillages()
    val mohallas: LiveData<List<String>> = propertyDao.getAllMohallas()

/*
    val villageCount= MutableLiveData<MutableList<Int>>()
*/

    /*init {
        CoroutineScope(Dispatchers.IO).launch {
            villageCount.value = countVillagesInCoy()!!
        }
    }*/

    suspend fun addProperty(property: Property){
        propertyDao.addProperty(property)
    }

    suspend fun updateProperty(property: Property){
        propertyDao.updateProperty(property)
    }

    suspend fun deleteProperty(property: Property){
        propertyDao.deleteProperty(property)
    }

    suspend fun deleteAll(){
        propertyDao.deleteAll()
    }

    /*suspend fun countVillagesInCoy(): MutableList<Int>? {
        var list: MutableList<Int> = mutableListOf()
        for(i in 0..coyList.value!!.size){
            list.add(propertyDao.getVillageCount(coyList.value!!.get(i)))
        }
        return list
    }*/
    suspend fun getVillages(coy: String): LiveData<List<String>> {
        return propertyDao.getVillages(coy)
    }

    suspend fun getMohallas(village: String): LiveData<List<String>> {
        return propertyDao.getMohallas(village)
    }



}