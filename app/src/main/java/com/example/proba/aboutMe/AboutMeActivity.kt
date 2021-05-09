package com.example.proba.aboutMe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proba.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutMeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        setView();
    }

    fun setView(){

    }
}