package com.army.saluteindia.data2.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.entities.MOHALLA
import com.army.saluteindia.data2.entities.VILLAGE

class villageWithMohallas (

    @Embedded val village: VILLAGE,
    @Relation(
        parentColumn = "id",
        entityColumn = "village_id"
    )
    val mohallas: List<MOHALLA>
)