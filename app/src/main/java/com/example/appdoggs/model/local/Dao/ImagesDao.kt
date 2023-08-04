package com.example.appdoggs.model.local.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appdoggs.model.local.Entities.DogsImages

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllImagesList(listBreed: List<DogsImages>)

    @Update
    suspend fun updateFavImages(dogsImages: DogsImages)

    @Query("SELECT * FROM images_table WHERE breed = :breed")
    fun getAllDoggiesImages(breed : String): LiveData<List<DogsImages>>

    // Funcion que trae todos los favoritos
    @Query("SELECT * FROM images_table WHERE fav = 1")
    fun getAllFavImages(): LiveData<List<DogsImages>>

}