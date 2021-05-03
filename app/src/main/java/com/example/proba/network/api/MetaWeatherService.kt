package com.example.proba.network.api

import com.example.proba.network.model.City
import com.example.proba.network.model.Day
import com.example.proba.network.model.Search
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MetaWeatherService {


    @GET("/api/location/{woeid}/{date}/")
    suspend fun getDay(@Path("woeid") world_location : String, @Path("date") date : String): List<Day>


    @GET("/api/location/search/")
    suspend fun getSearch(@Query("query") search_names : String) : List<Search>

    @GET("/api/location/{woeid}/")
    suspend fun getCity(@Path("woeid") city_location : String) : City


}