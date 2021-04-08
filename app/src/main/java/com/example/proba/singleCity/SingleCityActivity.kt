package com.example.proba.singleCity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proba.R
import com.example.proba.databinding.ActivitySingleCityBinding
import com.example.proba.databinding.ActivitySingleTetaBinding

class SingleCityActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySingleCityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySingleCityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Zagreb"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }



    }
}