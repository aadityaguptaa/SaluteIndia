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

    /*@Transaction
    @Query("SELECT * FROM btn_table where b_Name = :btn")
    suspend fun getBtnWithCoys(btn: String): List<BtnWithCoy>*/

    @Transaction
    @Query("SELECT * FROM village_table where id = :village")
    suspend fun getVillageWithMohallas(village: String): List<villageWithMohallas>

    @Transaction
    @Query("SELECT * FROM mohalla_table where id = :mohalla")
    suspend fun getMohallaWithHouses(mohalla: String): List<MohallaWithHouses>

    @Transaction
    @Query("SELECT * FROM coy_table where _id = :coy")
    suspend fun getCoyWithVillages(coy: String): List<CoyWithVillages>

    @Transaction
    @Query("SELECT * FROM coy_table where _id = :coy")
    suspend fun getCoyWithHouses(coy: String): List<CoyWithHouses>

    /*@Query("SELECT * FROM village_table where id = :village")
    suspend fun getVillageWithHouses(village: String): List<VillageWithHouses>*/

    @Transaction
    @Query("SELECT * FROM btn_table where b_Name = :btn")
    suspend fun getBtnWithHouses(btn: String): List<BtnWithHouses>

    @Query("SELECT * FROM coy_table")
    fun getCoyList(): LiveData<List<COY>>

    @Query("SELECT * FROM village_table")
    fun getVillageList(): LiveData<List<VILLAGE>>

    @Query("SELECT * FROM village_table WHERE coy_id = :id")
    fun getVillageList(id: String): LiveData<List<VILLAGE>>

    @Query("SELECT * FROM mohalla_table")
    fun getMohallaList(): LiveData<List<MOHALLA>>

    @Query("SELECT * FROM mohalla_table WHERE village_id = :id")
    fun getMohallaList(id: String): LiveData<List<MOHALLA>>

    @Query("SELECT * FROM house_table")
    fun getHouseList(): LiveData<List<HOUSES>>

    @Query("SELECT * FROM house_table WHERE mohalla_id = :id")
    fun getHouseList(id: String): LiveData<List<HOUSES>>

    @Query("SELECT id FROM village_table WHERE id = :id")
    suspend fun getVillageWithId(id: String): String

    @Query("SELECT id FROM mohalla_table WHERE id = :id")
    suspend fun getMohallaWithId(id: String): String

    @Query("SELECT * FROM PERSON_TABLE WHERE id = :id")
    suspend fun getPersonWithId(id: Int): PERSON

    @Query("SELECT * FROM HOUSE_TABLE WHERE house = :id")
    suspend fun getHouseWithId(id: String): HOUSES

    @Query("UPDATE house_table SET village_id = :villageName WHERE house = :houseId")
    suspend fun updateVillageOfHouse(villageName: String, houseId: String)

    @Query("UPDATE house_table SET mohalla_id = :mohallaName WHERE house = :houseId")
    suspend fun updateMohallaOfHouse(mohallaName: String, houseId: String)

    @Query("SELECT * FROM mohalla_table WHERE village_id = :id AND id = :mohalla")
    suspend fun getMohallaWithVillage(id: String, mohalla: String): MOHALLA

    @Query("SELECT * FROM person_table where name = :name")
    suspend fun getPersonWithName(name: String): PERSON

    @Query("UPDATE house_table SET husband_id = :id WHERE house = :houseId")
    suspend fun updateheadInHouse(id: Int, houseId: String)

    @Query("SELECT * FROM house_table WHERE mohalla_id = :id")
    suspend fun getHouseListNotLive(id: String): List<HOUSES>

}