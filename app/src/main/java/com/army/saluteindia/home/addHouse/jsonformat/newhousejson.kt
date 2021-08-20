package com.army.saluteindia.home.addHouse.jsonformat

data class newhousejson(
    val GR: String,
    val btn: String,
    val colour: String,
    val cowshed: Boolean,
    val coy: String,
    val entryPoints: String,
    val floor: String,
    val geo: Geo,
    val house: String,
    val mohalla: String,
    val nRooms: String,
    val perimeterfence: Boolean,
    val `property`: String,
    val relatives: Relatives,
    val village: String
)