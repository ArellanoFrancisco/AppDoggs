package com.example.appdoggs.model.local.Entities
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "breed_table")
data class Razas(@PrimaryKey val breed: String)