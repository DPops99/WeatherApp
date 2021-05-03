package com.example.proba.room.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proba.network.model.City

@Entity(tableName = "favorites")
data class Favorite (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @Embedded
    var city: City
    )