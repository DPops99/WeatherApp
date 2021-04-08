package com.example.proba.singleCity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.R
import com.example.proba.databinding.ActivitySingleCityBinding
import com.example.proba.databinding.ActivitySingleTetaBinding
import com.example.proba.databinding.SearchFragmentBinding
import com.example.proba.main.first_fragment.SearchFragment
import com.example.proba.network.model.City
import com.example.proba.network.model.Day
import com.example.proba.singleCity.adapter.ParentAdapter
import com.example.proba.singleCity.model.ChildModel
import com.example.proba.singleCity.model.ParentModel

class SingleCityActivity : AppCompatActivity() {


    private lateinit var binding : ActivitySingleCityBinding
    private lateinit var current_city : City
    private lateinit var current_day : List<Day>

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


        setView()
    }

    private fun setView() {

        intent?.getSerializableExtra(SearchFragment().city_bundle)?.let {
            current_city = intent.getSerializableExtra(SearchFragment().city_bundle) as City
            binding.scName.text = current_city.title
            binding.scTemp.text = """${current_city.consolidated_weather?.get(0)?.the_temp?.toInt().toString()}°"""
            binding.scWeather.text = current_city.consolidated_weather?.get(0)?.weather_state_name
            Log.d("WHATISSENT",intent.getSerializableExtra(SearchFragment().city_bundle).toString())

        }

        intent?.getSerializableExtra(SearchFragment().day_bundle)?.let{
            Log.d("WHATISSENT",intent.getSerializableExtra(SearchFragment().day_bundle).toString())
            current_day = intent.getSerializableExtra(SearchFragment().day_bundle) as List<Day>
        }





        binding.scRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = ParentAdapter(getData())
        }
    }

    private fun getData() : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()

        parents.add(ParentModel("Today",current_day))
        parents.add(ParentModel("Next 7 days",current_city.consolidated_weather!!))

        return parents
    }

    private fun getChildren(count : Int) : List<ChildModel>{
        val children = mutableListOf<ChildModel>()
        repeat(count){
            children.add(ChildModel("dijete"))
        }
        return children
    }
}