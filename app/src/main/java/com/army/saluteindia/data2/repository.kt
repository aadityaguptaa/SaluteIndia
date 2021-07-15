package com.army.saluteindia.data2

import androidx.lifecycle.LiveData
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE
import com.army.saluteindia.data2.relations.CoyWithVillages

class repository(private val propDao: PropDao) {

    val coyList: LiveData<List<COY>> = propDao.getCoyList()
    val villages: LiveData<List<VILLAGE>> = propDao.getVillageList()
    val mohallas: LiveData<List<MOHALLA>> = propDao.getMohallaList()


    suspend fun getVillages(coy: Int): LiveData<List<VILLAGE>> {
        return propDao.getVillageList(coy)
    }

    suspend fun getMohallas(village: Int): LiveData<List<MOHALLA>> {
        return propDao.getMohallaList(village)
    }
}