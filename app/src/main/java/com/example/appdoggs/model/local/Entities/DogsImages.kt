package com.example.appdoggs.model.local.Entities

import androidx.room.Entity

@Entity(tableName = "images_table", primaryKeys = ["breed", "imageUrl"])
data class DogsImages(
    val imageUrl: String,
    val breed: String,
    var fav: Boolean = false
)