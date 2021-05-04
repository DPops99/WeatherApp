package com.example.proba.room.dao


import androidx.room.*
import com.example.proba.network.model.City

@Dao
interface CityDao {

    @Query("SELECT * FROM cities WHERE isRecent = 1")
    suspend fun getAllCities() : List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities : List<City>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city :City)

    @Query("SELECT * FROM cities WHERE isFavorite = 1")
    suspend fun getFavoriteCities() : List<City>


}