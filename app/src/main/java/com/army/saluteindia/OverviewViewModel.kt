package com.army.saluteindia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.network.NewHouse
import com.army.saluteindia.network.RestApi
import com.army.saluteindia.network.villages.Data
import kotlinx.coroutines.*

class OverviewViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    val _coys = MutableLiveData<List<COY>>()

    val coys: LiveData<List<COY>>
        get() = _coys

    val _villages = MutableLiveData<List<Data>>()

    val villages: LiveData<List<Data>>
        get() = _villages

    val _villagesComplete = MutableLiveData<List<Data>>()

    val villagesComplete: LiveData<List<Data>>
        get() = _villagesComplete

    val _mohallas = MutableLiveData<List<com.army.saluteindia.network.mohallas.Data>>()

    val mohallas: LiveData<List<com.army.saluteindia.network.mohallas.Data>>
        get() = _mohallas

    val _houses2 = MutableLiveData<List<com.army.saluteindia.network.houses.Data>>()

    val houses2: LiveData<List<com.army.saluteindia.network.houses.Data>>
        get() = _houses2

    val _mohallasComplete = MutableLiveData<List<com.army.saluteindia.network.mohallas.Data>>()

    val mohallasComplete: LiveData<List<com.army.saluteindia.network.mohallas.Data>>
        get() = _mohallasComplete

    val _housesComplete = MutableLiveData<List<com.army.saluteindia.network.houses.Data>>()

    val housesComplete: LiveData<List<com.army.saluteindia.network.houses.Data>>
        get() = _housesComplete

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    suspend fun getHouses(){

        coroutineScope.launch {
            Log.i("asdf", "hello")
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getCoysFromAPI()
            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("housesOverview", _response.value!!)
                if(listResult.data.coy.isNotEmpty()){
                    _coys.value = listResult.data.coy
                }


            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("housesOverview", t.message.toString())
            }
        }
    }

    fun getVillages(coyName: String){
        coroutineScope.launch {
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getVillagesFromAPIWithCompanyFilter(coyName)

            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("asdf", _response.value!!)
                if(listResult.data != null){
                    _villages.value = listResult.data
                }


            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("asdf", t.message.toString())
            }
        }
    }

    fun getMohallas(villageName: String){
        coroutineScope.launch {
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getMohallasFromAPIWithVillageFilter(villageName)

            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("asdf", _response.value!!)
                if(listResult.data != null){
                    _mohallas.value = listResult.data
                }


            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("asdf", t.message.toString())
            }
        }
    }

    fun getHouses2(houseName: String){
        coroutineScope.launch {
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getHousesFromAPIWithMohallaFilter(houseName)

            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("asdf", _response.value!!)
                val lis = mutableListOf<com.army.saluteindia.network.houses.Data>()
                listResult.data.forEach {
                    if(it.mohalla == houseName){
                        lis.add(it)
                    }
                }
                if(listResult.data != null){
                    _houses2.value = lis
                }


            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("asdf", t.message.toString())
            }
        }
    }

    suspend fun getCompleteVillageList(){
        coroutineScope.launch {
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getVillagesList()
            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("villagesOverview", _response.value!!)
                if(listResult.data != null){
                    _villagesComplete.value = listResult.data
                }
            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("villagesOverview", t.message.toString())
            }
        }
    }

    suspend fun getCompleteMohallaList(){
        coroutineScope.launch {
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getMohallasList()
            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("mohallasOverview", _response.value!!)
                if(listResult.data != null){
                    _mohallasComplete.value = listResult.data
                }
            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("mohallasOverview", t.message.toString())
            }
        }
    }

    suspend fun getCompleteHousesList(){
        coroutineScope.launch {
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getHousesList()
            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("housesOverview", _response.value!!)
                if(listResult.data != null){
                    _housesComplete.value = listResult.data
                }
            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("housesOverview", t.message.toString())
            }
        }
    }

    /*suspend fun createHouse(newHouse: NewHouse){
        coroutineScope.launch {
            val getHouseDeferred = RestApi.RETROFIT_SERVICE.createHouse(newHouse)
            try{
                val listResult = getHouseDeferred.await()
                _response.value = listResult.toString()
                Log.i("housesOverview", _response.value!!)
                if(listResult != null){
                    Log.i("asdf", listResult.toString())
                }
            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("asdf", t.message.toString())
            }
        }
    }*/

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}