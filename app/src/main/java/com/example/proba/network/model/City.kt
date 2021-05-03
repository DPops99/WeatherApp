package com.example.proba.network.model

import androidx.room.*
import com.example.proba.room.converter.Converter
import com.google.gson.annotations.Expose
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "cities")
data class City (
    @PrimaryKey
    var woeid : Int,
    var title : String,
    var location_type : String,
    var latt_long : String,
    var time : String,
    var timezone_name : String,
    @TypeConverters(Converter::class)
    var consolidated_weather : List<Day>
        ) : Serializable
        {
            constructor(title: String, location_type: String, latt_long: String, time: String, timezone_name: String, woeid: Int) :
                    this(woeid, location_type, latt_long, time, timezone_name, title, ArrayList<Day>())
        }