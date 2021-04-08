package com.example.proba.main.view_model

import android.util.Log
import androidx.lifecycle.*
import com.example.proba.network.model.Day
import com.example.proba.network.model.Search
import com.example.proba.network.repository.Repository
import kotlinx.coroutines.launch

class ApiViewModel : ViewModel(){

    private val _api_days = MutableLiveData<List<Search>>()
    val api_days : LiveData<List<Search>> = _api_days

    init {
        viewModelScope.launch {
            _api_days.value = Repository().getSearch("london")
        }

    }


    fun get_api_search(search_name : String){
        Log.d("BACK_API","I'll be back")

        viewModelScope.launch {
            Log.d("BACK_API","I'm back")
            _api_days.value =  Repository().getSearch(search_name)
        }



    }
}