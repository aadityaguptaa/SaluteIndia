package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "village_table")
data class VILLAGE (

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var village_name: String,
    var mohalla_count: Int,
    var family_count: Int,
    var member_count: Int,
    var coy_id: Int

)