package com.example.proba.network.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Search (
    var title : String,
    var location_type : String,
    var latt_long : String,
    var woeid : Int,
    var distance : Int
    )