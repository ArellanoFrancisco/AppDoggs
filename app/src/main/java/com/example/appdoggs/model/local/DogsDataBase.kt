package com.example.appdoggs.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appdoggs.model.local.Dao.ImagesDao
import com.example.appdoggs.model.local.Dao.RazasDao
import com.example.appdoggs.model.local.Entities.DogsImages
import com.example.appdoggs.model.local.Entities.Razas

@Database(entities = [Razas::class, DogsImages::class], version = 1, exportSchema = false)
abstract class DogsDatabase : RoomDatabase() {

    abstract fun breedDao(): RazasDao
    abstract fun imagesDao(): ImagesDao

    companion object {

        @Volatile
        private var INSTANCE: DogsDatabase? = null

        fun getDatabase(context: Context): DogsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogsDatabase::class.java,
                    "dogs_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}