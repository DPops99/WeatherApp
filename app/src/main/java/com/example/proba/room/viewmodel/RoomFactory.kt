package com.example.proba.room.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.proba.room.database.AppDatabase

class RoomFactory(val roomDB : AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(RoomViewModel::class.java))
            return RoomViewModel(roomDB) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}