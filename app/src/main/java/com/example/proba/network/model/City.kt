package com.example.proba.network.model

import java.io.Serializable
import java.util.*

data class City (
    var title : String?,
    var location_type : String?,
    var latt_long : String?,
    var time : String?,
    var timezone_name : String?,
    var consolidated_weather : List<Day>?
        ) : Serializable