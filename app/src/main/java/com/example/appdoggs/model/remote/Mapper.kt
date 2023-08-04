package com.example.appdoggs.model.remote

import com.example.appdoggs.model.local.Entities.DogsImages
import com.example.appdoggs.model.local.Entities.Razas
import com.example.appdoggs.model.remote.FromInternet.IImages
import com.example.appdoggs.model.remote.FromInternet.IRazas


    fun fromInternetToBreedEntity(iRazas: IRazas): List<Razas> {

        return iRazas.message.map{
            Razas(breed = it) }
    }

    fun fromInternetToImagesEntity(iImages: IImages, breed: String): List<DogsImages> {
        return iImages.message.map {
            DogsImages(imageUrl = it, breed = breed) }
    }
