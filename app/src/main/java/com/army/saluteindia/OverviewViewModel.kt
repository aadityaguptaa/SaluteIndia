package com.army.saluteindia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.network.RestApi
import com.army.saluteindia.network.villages.Data
import kotlinx.coroutines.*

class OverviewViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    val _houses = MutableLiveData<List<COY>>()

    val houses: LiveData<List<COY>>
        get() = _houses

    val _villages = MutableLiveData<List<Data>>()

    val villages: LiveData<List<Data>>
        get() = _villages

    val _mohallas = MutableLiveData<List<com.army.saluteindia.network.mohallas.Data>>()

    val mohallas: LiveData<List<com.army.saluteindia.network.mohallas.Data>>
        get() = _mohallas

    val _houses2 = MutableLiveData<List<com.army.saluteindia.network.houses.Data>>()

    val houses2: LiveData<List<com.army.saluteindia.network.houses.Data>>
        get() = _houses2

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getHouses(){

        coroutineScope.launch {
            Log.i("asdf", "hello")
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getCoysFromAPI()
            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("asdf", _response.value!!)
                if(listResult.data.coy.isNotEmpty()){
                    _houses.value = listResult.data.coy
                }


            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("asdf", t.message.toString())
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
                if(listResult.data != null){
                    _houses2.value = listResult.data
                }


            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("asdf", t.message.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}