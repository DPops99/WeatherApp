package com.example.proba.network.model

data class Search (
    var title : String?,
    var location_type : String?,
    var latt_long : String?,
    var woeid : Int?,
    var distance : Int?
    )