package com.army.saluteindia.data2

import androidx.lifecycle.LiveData
import androidx.room.*
import com.army.saluteindia.data2.entities.*
import com.army.saluteindia.data2.relations.*

@Dao
interface PropDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBtn(btn: BTN)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoy(coy: COY)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVillage(village: VILLAGE)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMohalla(mohalla: MOHALLA)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHouse(house: HOUSES)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PERSON)

    @Transaction
    @Query("SELECT * FROM btn_table where b_Name = :btn")
    suspend fun getBtnWithCoys(btn: String): List<BtnWithCoy>

    @Transaction
    @Query("SELECT * FROM village_table where village_name = :village")
    suspend fun getVillageWithMohallas(village: String): List<villageWithMohallas>

    @Transaction
    @Query("SELECT * FROM mohalla_table where mohalla_name = :mohalla")
    suspend fun getMohallaWithHouses(mohalla: String): List<MohallaWithHouses>

    @Transaction
    @Query("SELECT * FROM coy_table where coy_Name = :coy")
    suspend fun getCoyWithVillages(coy: String): List<CoyWithVillages>

    @Transaction
    @Query("SELECT * FROM coy_table where coy_Name = :coy")
    suspend fun getCoyWithHouses(coy: String): List<CoyWithHouses>

    @Query("SELECT * FROM village_table where village_name = :village")
    suspend fun getVillageWithHouses(village: String): List<VillageWithHouses>

    @Transaction
    @Query("SELECT * FROM btn_table where b_Name = :btn")
    suspend fun getBtnWithHouses(btn: String): List<BtnWithHouses>

    @Query("SELECT * FROM coy_table")
    fun getCoyList(): LiveData<List<COY>>

    @Query("SELECT * FROM village_table")
    fun getVillageList(): LiveData<List<VILLAGE>>

    @Query("SELECT * FROM village_table WHERE coy_id = :id")
    fun getVillageList(id: Int): LiveData<List<VILLAGE>>

    @Query("SELECT * FROM mohalla_table")
    fun getMohallaList(): LiveData<List<MOHALLA>>

    @Query("SELECT * FROM mohalla_table WHERE village_id = :id")
    fun getMohallaList(id: Int): LiveData<List<MOHALLA>>

    @Query("SELECT * FROM house_table")
    fun getHouseList(): LiveData<List<HOUSES>>

    @Query("SELECT * FROM house_table WHERE mohalla_id = :id")
    fun getHouseList(id: Int): LiveData<List<HOUSES>>

}