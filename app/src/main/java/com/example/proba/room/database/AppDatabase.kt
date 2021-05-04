package com.example.proba.room.database

import android.content.Context
import androidx.room.*
import com.example.proba.network.model.City
import com.example.proba.room.converter.Converter
import com.example.proba.room.dao.CityDao

@Database(entities = [City::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun cityDao() : CityDao

    companion object{
        var instance: AppDatabase? =null

        fun getInstance(context: Context):AppDatabase?{
            if (instance == null){
                synchronized(AppDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return instance
        }


    }
}
