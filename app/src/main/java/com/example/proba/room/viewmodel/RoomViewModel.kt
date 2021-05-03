package com.example.proba.room.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.network.model.City
import com.example.proba.room.database.AppDatabase
import com.example.proba.room.model.Favorite
import kotlinx.coroutines.launch

class RoomViewModel(roomDB : AppDatabase): ViewModel() {


    val cities = MutableLiveData<List<City>>()
    val fav_cities = MutableLiveData<List<City>>()
    val roomDB : AppDatabase


    init {
        this.roomDB = roomDB
        getCities()
        getFavCities()
    }



    fun getCities(){
        viewModelScope.launch {
            cities.value = roomDB.cityDao().getAllCities()
        }
    }

    fun saveAndGetFavCity(apiCity: City){
        viewModelScope.launch {
            roomDB.cityDao().insertCity(apiCity)
            fav_cities.value = roomDB.cityDao().getFavoriteCities()
            Log.d("FAVCITY_SAVED",cities.value.toString())
        }
    }

    fun insertCities(cities : List<City>){
        viewModelScope.launch {
            roomDB.cityDao().insertAll(cities)
        }
    }

    fun saveAndGetCities(apiCity: City){
        viewModelScope.launch {
            roomDB.cityDao().insertCity(apiCity)
            cities.value = roomDB.cityDao().getAllCities()
//            fav_cities.value = roomDB.cityDao().getFavoriteCities()
            Log.d("CITY_SAVED",cities.value.toString())
        }
    }

    fun saveAndGetCities(apiCities: List<City>){
        viewModelScope.launch {
            roomDB.cityDao().insertAll(apiCities)
            cities.value = roomDB.cityDao().getAllCities()
        }
    }

    fun saveAndGetFavCities(fav : Favorite){
        viewModelScope.launch {
            roomDB.favoriteDao().insertFavorite(fav)
            fav_cities.value = roomDB.cityDao().getFavoriteCities()
        }
    }

    fun deleteAndGetFavCities(fav: Favorite){
        viewModelScope.launch {
            roomDB.favoriteDao().deleteFavorite(fav.city.woeid)
            fav_cities.value = roomDB.cityDao().getFavoriteCities()
        }
    }

    fun getFavCities(){
        viewModelScope.launch {
            fav_cities.value = roomDB.cityDao().getFavoriteCities()
        }
    }


}