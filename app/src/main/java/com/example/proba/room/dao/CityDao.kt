package com.example.proba.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proba.network.model.City

@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    suspend fun getAllCities() : List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities : List<City>)
}