package com.army.saluteindia.data2.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.army.saluteindia.data2.entities.HOUSES
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE

data class VillageWithHouses (
    @Embedded val village: VILLAGE,
    @Relation(
        parentColumn = "id",
        entityColumn = "village_id"
    )
    val houses: List<HOUSES>

)