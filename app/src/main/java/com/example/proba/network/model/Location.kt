package com.example.proba.network.model

import java.util.*

data class Location (
    var title : String?,
    var location_type : String?,
    var latt_long : String?,
    var time : Date?,
    var timezone_name : String?,
    var consolidated_weather : List<Day>?
        )