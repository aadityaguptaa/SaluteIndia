package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coy_table")
data class COY (

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var coy_Name: String,
    var btn_id: Int
    )