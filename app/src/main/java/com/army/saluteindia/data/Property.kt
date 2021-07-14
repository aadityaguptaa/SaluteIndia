package com.army.saluteindia.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "property")
data class Property (

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var Bn: String,
    var COY: String,
    var Village: String,
    var Mohalla: String,
    @ColumnInfo(name = "House No")
    var HouseNo: String,
    var Name: String,
    var Relation: String,
    var Sex: String,
    var Age: Int,
    var Occupation: String,
    @ColumnInfo(name = "Mob No")
    var mobNo: String,
    var GR: String,
    var Property: String,
    var Floor: String,
    var Colour: String,
    @ColumnInfo(name = "No of Rooms")
    var noOfRooms: Int,
    @ColumnInfo(name = "Perimeter Fence")
    var perimeterFence: String,
    var Cowshed: String,
    @ColumnInfo(name = "Entry points")
    var entrypoint: Int,
    @ColumnInfo(name = "LAT/LONG")
    var latlong: String
): Parcelable