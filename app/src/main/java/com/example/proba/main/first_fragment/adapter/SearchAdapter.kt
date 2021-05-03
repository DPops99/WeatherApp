package com.example.proba.main.first_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proba.R
import com.example.proba.databinding.CityItemViewBinding
import com.example.proba.network.model.City
import java.lang.Math.round

class SearchAdapter(var cities : ArrayList<City>, val context : Context, var listener: SearchAdapter.OnItemClickListener?, var long_listener: OnItemLongClickListener)  : RecyclerView.Adapter<SearchAdapter.SearchHolder>(){

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

        if (cities[position].favorite)
            holder.binding.imgStar.load(R.drawable.ic_star_1)
        else
            holder.binding.imgStar.load(R.drawable.ic_star_0)

        if (listener!=null){
            holder.binding.imgStar.setOnClickListener {


                    if (cities[position].favorite){
                        holder.binding.imgStar.load(R.drawable.ic_star_0)
                    }
                    else
                        holder.binding.imgStar.load(R.drawable.ic_star_1)
                    listener?.onItemClick(position, !cities[position].favorite)
            }
        }


    }

    override fun getItemCount(): Int {
        return cities.size
    }

    inner class SearchHolder(val binding: CityItemViewBinding) : RecyclerView.ViewHolder(binding.root), View.OnLongClickListener{
        init {
            binding.root.setOnLongClickListener(this)
        }
        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if(position!= RecyclerView.NO_POSITION)
                long_listener.onItemLongClick(position)
            return true
        }
    }


    interface OnItemClickListener{
        fun onItemClick(position: Int, isFav : Boolean)
    }

    interface OnItemLongClickListener{
        fun onItemLongClick(position: Int)
    }

    fun moveItem(from : Int, to : Int){
        val from_fav = cities[from]

        cities[from] = cities[to]
        cities[to] = from_fav

    }

}