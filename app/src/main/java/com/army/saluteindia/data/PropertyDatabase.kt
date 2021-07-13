package com.army.saluteindia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Property::class], version = 1, exportSchema = false)
abstract class PropertyDatabase: RoomDatabase() {

    abstract fun propertyDao(): PropertyDao

    companion object{
        @Volatile
        private var INSTANCE: PropertyDatabase? = null

        fun getDatabase(context: Context): PropertyDatabase{
            var tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }else{
                var instance = Room.databaseBuilder(
                    context.applicationContext,
                    PropertyDatabase::class.java,
                    "property_database"
                ).createFromAsset("property.db")
                    .build()
                INSTANCE = instance
                return instance
            }

        }

    }
}