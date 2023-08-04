package com.example.appdoggs.model.local.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_table")
data class DogsImages
    (@PrimaryKey val imageUrl: String,
     val breed: String,
     var fav: Boolean = false)