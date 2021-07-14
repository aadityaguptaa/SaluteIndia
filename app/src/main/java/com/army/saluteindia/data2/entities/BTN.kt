package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "btn_table")
data class BTN (

    @PrimaryKey(autoGenerate = false)
    var id: Int,

    var b_Name: String

)