package com.army.saluteindia.data2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.army.saluteindia.data2.entities.COY
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



    init{
        repository = repository(propertyDao)
        coyList = repository.coyList
        villages = repository.villages
        mohallas = repository.mohallas
    }

    fun getVillages(coy: Int){
        viewModelScope.launch(Dispatchers.IO) {
            villages = repository.getVillages(coy)
        }
    }

    fun getMohallas(village: Int){
        viewModelScope.launch(Dispatchers.IO) {
            mohallas = repository.getMohallas(village)
        }
    }


}