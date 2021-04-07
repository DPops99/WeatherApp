package com.example.proba.main.second_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.proba.R
import com.example.proba.databinding.TetaViewItemBinding
import com.example.proba.main.model.TetaMenza
import com.example.proba.network.model.Day

class WeatherAdapter(var days : List<Day>, val contex : Context, var listener: WeatherAdapter.OnItemClickListener)  : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(TetaViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.binding.tetaName.text = days[position].weather_state_name
        holder.binding.tetaJobPosition.text = days[position].air_pressure.toString()
        holder.binding.tetaImg.load(R.drawable.ic_c){
            crossfade(true)
            placeholder(R.drawable.ic_baseline_people_24)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    inner class WeatherHolder(val binding: TetaViewItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
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