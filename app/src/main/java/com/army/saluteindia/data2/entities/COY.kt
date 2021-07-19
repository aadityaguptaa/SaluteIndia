package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coy_table")
data class COY (

    @PrimaryKey(autoGenerate = false)
    var _id: String,
    var villagesCount: Int,
    var mohallasCount: Int,
    var housesCount: Int

)