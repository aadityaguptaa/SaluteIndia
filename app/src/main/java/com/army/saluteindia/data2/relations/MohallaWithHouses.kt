package com.army.saluteindia.data2.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE

data class MohallaWithHouses (

    @Embedded val mohalla: MOHALLA,
    @Relation(
        parentColumn = "id",
        entityColumn = "mohalla_id"
    )
    val houses: List<HOUSES>
)