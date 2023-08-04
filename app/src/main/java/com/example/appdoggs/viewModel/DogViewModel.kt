package com.example.appdoggs.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.appdoggs.model.DogRepository
import com.example.appdoggs.model.local.DogsDatabase
import com.example.appdoggs.model.local.Entities.DogsImages
import com.example.appdoggs.model.local.Entities.Razas
import kotlinx.coroutines.launch

class DogViewModel(application: Application): AndroidViewModel(application) {

    private val repository: DogRepository

    init {
        val db = DogsDatabase.getDatabase(application)
        val breedDao = db.breedDao()
        val imageDao = db.imagesDao()
        repository = DogRepository(imageDao ,breedDao)

        viewModelScope.launch {
            repository.fetchBreed()
        }
    }

    //Todas las razas de perro desde la DataBase
    fun getBreedList(): LiveData<List<Razas>> = repository.breedListLivedata

    // Para las images
    private var breedSelected : String = ""

    fun getImagesByBreedFromInternet(breed: String) = viewModelScope.launch {
        breedSelected = breed
        repository.fetchDogImages(breed)
    }

    fun getImages(): LiveData<List<DogsImages>> = repository.getAllImagesByBreed(breedSelected)


    fun updateFav(dogsImages: DogsImages) = viewModelScope.launch {
        repository.updateFavImages(dogsImages)
    }

}