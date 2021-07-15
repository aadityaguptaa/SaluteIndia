package com.army.saluteindia.data2.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.army.saluteindia.data2.entities.BTN
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE

data class BtnWithHouses (
    @Embedded val btn: BTN,
    @Relation(
        parentColumn = "id",
        entityColumn = "btn_id"
    )
    val houses: List<HOUSES>


)