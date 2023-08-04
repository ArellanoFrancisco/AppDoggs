package com.example.appdoggs.model.local.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appdoggs.model.local.Entities.Razas
import com.example.appdoggs.model.remote.FromInternet.IRazas

@Dao
interface RazasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreedList(listBreed : List<Razas>)

    @Query("SELECT * FROM breed_table ORDER BY breed ASC")
    fun getAllBreedList(): LiveData<List<Razas>>

}