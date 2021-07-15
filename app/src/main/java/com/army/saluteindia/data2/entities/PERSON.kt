package com.army.saluteindia.data2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class PERSON (

    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var age: Int,
    var tel: String
)