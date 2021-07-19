package com.army.saluteindia.data2.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE

data class CoyWithHouses (
    @Embedded val coy: COY,
    @Relation(
        parentColumn = "_id",
        entityColumn = "coy_id"
    )
    val houses: List<HOUSES>
)