package com.example.appdoggs.model.remote.FromInternet

import com.google.gson.annotations.SerializedName

data class IImages(
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String
)