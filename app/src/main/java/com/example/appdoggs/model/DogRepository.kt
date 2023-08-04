package com.example.appdoggs.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.appdoggs.model.local.Dao.ImagesDao
import com.example.appdoggs.model.local.Dao.RazasDao
import com.example.appdoggs.model.local.Entities.DogsImages
import com.example.appdoggs.model.local.Entities.Razas
import com.example.appdoggs.model.remote.RetrofitClient
import com.example.appdoggs.model.remote.fromInternetToBreedEntity
import com.example.appdoggs.model.remote.fromInternetToImagesEntity

class DogRepository(private val imagesDao: ImagesDao, private val razasDao: RazasDao) {

    private val networkService = RetrofitClient.retrofitInstance()
    val breedListLivedata = razasDao.getAllBreedList()

    suspend fun fetchBreed() {
        val service = kotlin.runCatching { networkService.getBreedsList() }
        service.onSuccess {
            when (it.code()) {
                200 -> it.body()?.let {
                    razasDao.insertAllBreedList(fromInternetToBreedEntity(it))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }


    //Recibe la raza y realiza la solicitud guardando el elemento en la Base de datos.
    suspend fun fetchDogImages(breed: String) {
        val service = kotlin.runCatching { networkService.getImagesList(breed) }
        service.onSuccess {
            when (it.code()) {
                200 -> it.body()?.let {
                    imagesDao.insertAllImagesList(fromInternetToImagesEntity(it, breed))
                }
                else -> Log.d("REPO-IMG", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    // Retorna las imagenes por raza desde la base de datos.
    fun getAllImagesByBreed(breed: String): LiveData<List<DogsImages>> {
        return imagesDao.getAllDoggiesImages(breed)
    }

    suspend fun updateFavImages(dogsImages: DogsImages) {
        imagesDao.updateFavImages(dogsImages)
    }

}