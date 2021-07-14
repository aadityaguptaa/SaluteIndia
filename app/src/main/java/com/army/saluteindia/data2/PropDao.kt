package com.army.saluteindia.data2

import androidx.room.*
import com.army.saluteindia.data2.entities.BTN
import com.army.saluteindia.data2.entities.COY
import com.army.saluteindia.data2.relations.BtnWithCoy

@Dao
interface PropDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBtn(btn: BTN)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoy(coy: COY)

    @Transaction
    @Query("SELECT * FROM btn_table where b_Name = :btn")
    suspend fun getBtnWithCoys(btn: String): List<BtnWithCoy>
}