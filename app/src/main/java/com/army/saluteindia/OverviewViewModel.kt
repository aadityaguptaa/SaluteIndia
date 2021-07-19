package com.army.saluteindia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.network.RestApi
import kotlinx.coroutines.*

class OverviewViewModel: ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

     val _houses = MutableLiveData<List<COY>>()

    val houses: LiveData<List<COY>>
        get() = _houses

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getHouses()
    }

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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}