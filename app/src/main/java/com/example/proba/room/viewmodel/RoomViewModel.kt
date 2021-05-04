package com.example.proba.room.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proba.network.model.City
import com.example.proba.room.database.AppDatabase
import kotlinx.coroutines.launch

class RoomViewModel(context: Context): ViewModel() {


    val cities = MutableLiveData<List<City>>()
    val fav_cities = MutableLiveData<List<City>>()
    val roomDB : AppDatabase


    init {
        this.roomDB = AppDatabase.getInstance(context)!!
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


    fun saveAndGetCities(apiCity: City){
        viewModelScope.launch {
            roomDB.cityDao().insertCity(apiCity)
            cities.value = roomDB.cityDao().getAllCities()
            Log.d("CITY_SAVED",cities.value.toString())
        }
    }


    fun getFavCities(){
        viewModelScope.launch {
            fav_cities.value = roomDB.cityDao().getFavoriteCities()
        }
    }


}