package com.army.saluteindia.data2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.army.saluteindia.data2.entities.*

@Database(
    entities = [
        BTN::class,
        COY::class,
        VILLAGE::class,
        MOHALLA::class,
        PERSON::class,
        HOUSES::class
    ],
    version = 1
)
abstract class database: RoomDatabase() {

    abstract val dao: PropDao

    companion object{
        @Volatile
        private var INSTANCE: database? = null

        fun getInstance(context: Context): database{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    "prop_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}