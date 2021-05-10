package com.example.proba.room.converter

import androidx.room.TypeConverter
import com.example.proba.network.model.City
import com.example.proba.network.model.Day
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun listToJson(value:List<Day>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Day>::class.java).toList()
}