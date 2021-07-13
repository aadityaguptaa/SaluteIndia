package com.army.saluteindia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProperty(property: Property)

    @Query("SELECT * FROM property ORDER BY id ASC")
    fun getPropertyList(): LiveData<List<Property>>

    @Update
    suspend fun updateProperty(property: Property)

    @Delete
    suspend fun deleteProperty(property: Property)

    @Query("DELETE FROM property")
    suspend fun deleteAll()

    @Query("SELECT DISTINCT(COY) FROM property ORDER BY COY ASC")
    fun getCoyList(): LiveData<List<String>>

    @Query( "SELECT COUNT(DISTINCT(Village)) FROM property where COY = :coy")
    fun getVillageCount(coy: String): Int

    @Query("SELECT DISTINCT(Village) FROM property WHERE COY = :coy ")
    fun getVillages(coy:String): LiveData<List<String>>

    @Query("SELECT DISTINCT(Village) FROM property ")
    fun getAllVillages(): LiveData<List<String>>

    @Query("SELECT DISTINCT(Mohalla) FROM property WHERE Village = :village ")
    fun getMohallas(village:String): LiveData<List<String>>

    @Query("SELECT DISTINCT(Mohalla) FROM property ")
    fun getAllMohallas(): LiveData<List<String>>

    @Query("SELECT `LAT/LONG` FROM property WHERE Mohalla = :mohalla ")
    fun getlatlongs(mohalla:String): LiveData<List<String>>

    @Query("SELECT `LAT/LONG` FROM property")
    fun getAlllatlongs(): LiveData<List<String>>



}