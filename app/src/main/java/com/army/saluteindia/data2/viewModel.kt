package com.army.saluteindia.data2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class viewModel(application: Application): AndroidViewModel(application) {

    private lateinit var repository: repository
    private var propertyDao: PropDao = database.getInstance(application).dao
    lateinit var coyList: LiveData<List<COY>>
    lateinit var villages: LiveData<List<VILLAGE>>
    lateinit var mohallas: LiveData<List<MOHALLA>>
    lateinit var houses: LiveData<List<HOUSES>>



    init{
        repository = repository(propertyDao)
        coyList = repository.coyList
        villages = repository.villages
        mohallas = repository.mohallas
        houses = repository.houses

    }

    fun getVillages(coy: String){
        viewModelScope.launch(Dispatchers.IO) {
            villages = repository.getVillages(coy)
        }
        Log.d("villages", "getVillages() from viewModel called")
    }

    fun getMohallas(village: String){
        viewModelScope.launch(Dispatchers.IO) {
            mohallas = repository.getMohallas(village)
        }
    }

    fun getHouses(mohalla: String){
        viewModelScope.launch(Dispatchers.IO) {
            houses = repository.getHouses(mohalla)
        }
    }


}