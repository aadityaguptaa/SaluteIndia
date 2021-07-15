package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coy_table")
data class COY (

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var coy_Name: String,
    var village_count: Int,
    var mohalla_count: Int,
    var family_count: Int,
    var members_count: Int,
    var btn_id: Int

)