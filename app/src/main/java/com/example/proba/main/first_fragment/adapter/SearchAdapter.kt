package com.example.proba.main.first_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.databinding.CityItemViewBinding
import com.example.proba.network.model.Search

class SearchAdapter(var cities : List<Search>, val contex : Context, var listener: OnItemClickListener)  : RecyclerView.Adapter<SearchAdapter.SearchHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        return SearchHolder(CityItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.binding.cityName.text = cities[position].title
        holder.binding.cityDistance.text = cities[position].distance.toString()
        holder.binding.cityLattLong.text = cities[position].latt_long

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