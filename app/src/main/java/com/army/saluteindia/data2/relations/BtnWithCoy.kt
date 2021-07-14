package com.army.saluteindia.data2.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.army.saluteindia.data2.entities.BTN
import com.army.saluteindia.data2.entities.COY

data class BtnWithCoy (

    @Embedded val btn: BTN,
    @Relation(
        parentColumn = "id",
        entityColumn = "btn_id"
    )
    val coys: List<COY>
)