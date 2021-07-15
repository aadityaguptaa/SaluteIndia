package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mohalla_table")
data class MOHALLA(

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var mohalla_name: String,
    var family_count: Int,
    var member_count: Int,
    var village_id: Int
)