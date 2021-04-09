package com.example.proba.main.first_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proba.databinding.CityItemViewBinding
import com.example.proba.network.model.City
import com.example.proba.network.model.Search
import java.lang.Math.round

class SearchAdapter(var cities : List<City>, val context : Context, var listener: OnItemClickListener)  : RecyclerView.Adapter<SearchAdapter.SearchHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(CityItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.binding.cityName.text = cities[position].title
        holder.binding.cityDistance.text = cities[position].timezone_name

        val ll = cities[position].latt_long!!.split(",")
        holder.binding.cityLattLong.text = """${(round(ll[0].toFloat() * 100) / 100)} ${(round(ll[1].toFloat() * 100) / 100)}"""

        holder.binding.cityTemp.text = """${cities[position].consolidated_weather?.get(0)?.the_temp?.toInt().toString()}°"""

        var id = "@drawable/ic_"+cities[position].consolidated_weather?.get(0)?.weather_state_abbr
        var img_res = context.resources.getIdentifier(id,null,context.packageName)
        var logo_draw = context.resources.getDrawable(img_res)
        holder.binding.cityWImg.load(logo_draw)

    }

    override fun getItemCount(): Int {
        return cities.size
    }

    inner class SearchHolder(val binding: CityItemViewBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init {
            binding.root.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!= RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}