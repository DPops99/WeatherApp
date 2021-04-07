package com.example.proba.main.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.proba.network.model.Day
import com.example.proba.network.repository.Repository

class ApiViewModel : ViewModel(){

    var api_days : LiveData<List<Day>> = liveData {
        emit(getApiDays(44418,"2013/4/27"))
    }


    suspend fun getApiDays(location : Int, date : String):List<Day>{
        return Repository().getDay(location, date)
    }
}