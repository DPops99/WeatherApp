package com.example.proba.network.model

import java.util.*

data class Day (
    var id : Int?,
    var applicable_date : Date?,
    var weather_state_name : String?,
    var weather_state_abbr : String?,
    var wind_speed : Float?,
    var wind_direction : Float?,
    var min_temp : Float?,
    var max_temp : Float?,
    var the_temp : Float?,
    var air_pressure : Float?,
    var humidity : Float?,
    var visibility : Float?,
    var predictability : Int?
        )