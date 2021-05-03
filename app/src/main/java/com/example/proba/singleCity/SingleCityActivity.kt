package com.example.proba.singleCity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proba.R
import com.example.proba.databinding.ActivitySingleCityBinding
import com.example.proba.main.first_fragment.SearchFragment
import com.example.proba.network.model.City
import com.example.proba.network.model.Day
import com.example.proba.singleCity.adapter.ParentAdapter
import com.example.proba.singleCity.model.ParentModel
import java.text.SimpleDateFormat
import kotlin.math.round

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

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


        setView()
    }

    private fun setView() {


        intent?.getSerializableExtra(SearchFragment().city_bundle)?.let {
            current_city = intent.getSerializableExtra(SearchFragment().city_bundle) as City

            val date = SimpleDateFormat("E, MMMM d").format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(current_city.time!!))
            val time = SimpleDateFormat("hh:mm a").format(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(current_city.time!!))

            binding.scName.text = current_city.title
            binding.scTemp.text = """${current_city.consolidated_weather?.get(0)?.the_temp?.toInt().toString()}°"""
            binding.scWeather.text = current_city.consolidated_weather?.get(0)?.weather_state_name
            binding.scDate.text = date.capitalizeWords()
            binding.scTime.text = """$time (${current_city.timezone_name})"""

            var id = "@drawable/ic_"+current_city.consolidated_weather?.get(0)?.weather_state_abbr
            var img_res = applicationContext.resources.getIdentifier(id,null,applicationContext.packageName)
            var logo_draw = applicationContext.resources.getDrawable(img_res)
            binding.scWeatherImg.load(logo_draw){
                placeholder(R.drawable.ic_wind)
            }

            binding.wiTemp.wDesc.text = getString(R.string.min_max_temp)
            binding.wiTemp.wIcon.load(R.drawable.ic_thermostat)
            binding.wiTemp.wValue.text = """${current_city.consolidated_weather?.get(0)?.min_temp?.toInt().toString()} / ${current_city.consolidated_weather?.get(0)?.max_temp?.toInt().toString()}"""

            binding.wiWind.wDesc.text = getString(R.string.wind)
            binding.wiWind.wIcon.load(R.drawable.ic_wind)
            binding.wiWind.wValue.text = """${round(current_city.consolidated_weather?.get(0)?.wind_speed!!).toInt()} km/H (${current_city.consolidated_weather?.get(0)?.wind_direction_compass})"""

            binding.wiHum.wDesc.text = getString(R.string.humidity)
            binding.wiHum.wIcon.load(R.drawable.ic_humidity)
            binding.wiHum.wValue.text = """${current_city.consolidated_weather?.get(0)?.humidity?.toString()}%"""

            binding.wiPress.wDesc.text = getString(R.string.pressure)
            binding.wiPress.wIcon.load(R.drawable.ic_pressure)
            binding.wiPress.wValue.text = """${round(current_city.consolidated_weather?.get(0)?.air_pressure!!).toInt()} hPa"""

            binding.wiVis.wDesc.text = getString(R.string.visibilty)
            binding.wiVis.wIcon.load(R.drawable.ic_visibility)
            binding.wiVis.wValue.text = """${round(current_city.consolidated_weather?.get(0)?.visibility!!).toInt()} km"""

            binding.wiAcc.wDesc.text = getString(R.string.accuracy)
            binding.wiAcc.wIcon.load(R.drawable.ic_accuracy)
            binding.wiAcc.wValue.text =  """${current_city.consolidated_weather?.get(0)?.predictability?.toString()}%"""


        }

        intent?.getSerializableExtra(SearchFragment().day_bundle)?.let{
            current_day = intent.getSerializableExtra(SearchFragment().day_bundle) as List<Day>
        }





        binding.scRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = ParentAdapter(getData(), context)
        }
    }

    private fun getData() : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()

        parents.add(ParentModel(getString(R.string.today),current_day))
        parents.add(ParentModel(getString(R.string.next_days),current_city.consolidated_weather!!.subList(1, current_city.consolidated_weather!!.size)))

        return parents
    }

    fun String.capitalizeWords() : String = split(" ").joinToString(" "){it.capitalize()}
}