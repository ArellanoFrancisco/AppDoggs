package com.example.appdoggs.model.remote

import com.example.appdoggs.model.local.Entities.DogsImages
import com.example.appdoggs.model.local.Entities.Razas
import com.example.appdoggs.model.remote.FromInternet.IImages
import com.example.appdoggs.model.remote.FromInternet.IRazas


    fun fromInternetToBreedEntity(iRazas: IRazas): List<Razas> {
        val breedNames = iRazas.message.keys
        return breedNames.map{breedNames->

            Razas(breed = breedNames) }
    }

    fun fromInternetToImagesEntity(iImages: IImages, breed: String): List<DogsImages> {
        val imageName= iImages.message
        return imageName.map {imageName ->
            DogsImages(imageUrl = imageName, breed = breed) }
    }
