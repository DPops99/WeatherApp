package com.example.proba.main.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.proba.network.model.City
import com.example.proba.network.model.Day
import com.example.proba.network.model.Search
import com.example.proba.network.repository.Repository
import kotlinx.coroutines.launch
import java.util.*

class ApiViewModel : ViewModel(){

    val api_search = MutableLiveData<List<Search>>()
    val api_city = MutableLiveData<City>()
    val api_day = MutableLiveData<List<Day>>()

//    val api_days : LiveData<List<Search>> = _api_city

//    init {
//        viewModelScope.launch {
//            api_search.value = Repository().getSearch("london")
//        }
//    }


    fun get_api_search(search_name : String){
        Log.d("BACK_API","I'll be back")

        viewModelScope.launch {
            Log.d("BACK_API","I'm back")
            api_search.value =  Repository().getSearch(search_name)
        }
    }

    fun get_api_city(location : Int){
        Log.d("CITY_API","I'll be back")
        viewModelScope.launch {
            api_city.value = Repository().getCity(location)
            Log.d("CITY_API","I'm back")
        }
    }

    fun get_api_day(location : Int, date : String){
        Log.d("DAY_API",date)
        viewModelScope.launch {
            api_day.value = Repository().getDay(location, date)
            Log.d("DAY_API",api_day.value.toString())
        }
    }


}