package com.example.proba.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.proba.room.model.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites() : List<Favorite>

    @Insert
    suspend fun insertFavorite(city : Favorite)

//    @Delete
//    suspend fun deleteFavorite(city: Favorite)

    @Query("DELETE FROM favorites WHERE woeid LIKE :city_id")
    suspend fun deleteFavorite(city_id : Int)
}