package com.example.proba.network.repository

import com.example.proba.network.api.MetaWeatherService
import com.example.proba.network.model.Day
import com.example.proba.network.model.Search
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private val service : MetaWeatherService
    private val BASE_URL = "https://www.metaweather.com/api/"

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(MetaWeatherService::class.java)
    }

    suspend fun getDay(world_location : Int, date: String): List<Day> {
        return service.getDay(world_location.toString(), date)
    }

    suspend fun getSearch(search_name : String):List<Search>{
        return service.getSearch(search_name)
    }


}