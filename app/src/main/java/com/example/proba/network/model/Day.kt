package com.example.proba.network.model

import java.io.Serializable
import java.util.*

data class Day (
    var id : String?,
    var applicable_date : String?,
    var weather_state_name : String?,
    var weather_state_abbr : String?,
    var wind_speed : Float?,
    var wind_direction : Float?,
    var min_temp : Float?,
    var max_temp : Float?,
    var the_temp : Float?,
    var air_pressure : Float?,
    var humidity : Int?,
    var visibility : Float?,
    var predictability : Int?
        ): Serializable