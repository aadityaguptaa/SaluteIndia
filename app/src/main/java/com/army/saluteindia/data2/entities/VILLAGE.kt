package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "village_table")
data class VILLAGE (

    @PrimaryKey(autoGenerate = false)
    var id: String,
    var mohalla_count: Int,
    var family_count: Int,
    var member_count: Int,
    var coy_id: String

)