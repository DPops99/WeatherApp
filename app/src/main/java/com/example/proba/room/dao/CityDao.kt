package com.example.proba.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proba.network.model.City

@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    suspend fun getAllCities() : List<City>

    @Insert
    suspend fun insertAll(cities : List<City>)
}