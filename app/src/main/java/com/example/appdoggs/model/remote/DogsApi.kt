package com.example.appdoggs.model.remote

import com.example.appdoggs.model.remote.FromInternet.IImages
import com.example.appdoggs.model.remote.FromInternet.IRazas
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {

    @GET("breeds/list/all")
    suspend fun getBreedsList(): Response<IRazas>

    @GET("breed/{breed}/images")
    suspend fun getImagesList(@Path("breed") breed: String): Response<IImages>


}