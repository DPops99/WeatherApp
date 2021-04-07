package com.example.proba.network.api

import com.example.proba.network.model.Day
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface MetaWeatherService {


    @GET("/api/location/{woeid}/{date}/")
    suspend fun getDay(@Path("woeid") world_location : String, @Path("date") date : String): List<Day>

}