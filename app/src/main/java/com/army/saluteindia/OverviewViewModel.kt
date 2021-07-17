package com.army.saluteindia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.network.Coy
import com.army.saluteindia.network.RestApi
import kotlinx.coroutines.*

class OverviewViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _houses = MutableLiveData<List<Coy>>()

    val houses: LiveData<List<Coy>>
        get() = _houses

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getHouses()
    }

    private fun getHouses(){

        coroutineScope.launch {
            val getPropertiesDeferred = RestApi.RETROFIT_SERVICE.getHousesFromAPI()
            try{
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.toString()
                Log.i("asdf", _response.value!!)
                /*if(listResult.houses.isNotEmpty()){
                    _houses.value = listResult.houses
                }*/

            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
                Log.i("info", t.message.toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}