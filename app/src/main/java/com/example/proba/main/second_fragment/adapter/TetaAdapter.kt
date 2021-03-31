package com.example.proba.main.second_fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.proba.R
import com.example.proba.databinding.TetaViewItemBinding
import com.example.proba.main.model.TetaMenza

class TetaAdapter(var tete : ArrayList<TetaMenza>, val contex : Context, var listener: TetaAdapter.OnItemClickListener) : RecyclerView.Adapter<TetaAdapter.TetaHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TetaHolder {
        return TetaHolder(TetaViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: TetaHolder, position: Int) {
        holder.binding.tetaName.text = tete[position].first_name+" "+tete[position].last_name
        holder.binding.tetaJobPosition.text = tete[position].job_position.toString()
        holder.binding.tetaImg.load(tete[position].image){
            crossfade(true)
            placeholder(R.drawable.ic_baseline_people_24)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int {
        return tete.size
    }

    inner class TetaHolder(val binding: TetaViewItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init {
            binding.root.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }


    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }


}