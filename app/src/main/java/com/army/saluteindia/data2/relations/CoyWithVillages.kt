package com.army.saluteindia.data2.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.army.saluteindia.data2.entities.BTN
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.VILLAGE

data class CoyWithVillages (
    @Embedded val coy: COY,
    @Relation(
        parentColumn = "_id",
        entityColumn = "coy_id"
    )
    val villages: List<VILLAGE>
)