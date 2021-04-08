package com.example.proba.singleCity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.databinding.ChildItemBinding
import com.example.proba.network.model.Day
import com.example.proba.singleCity.model.ChildModel

class ChildAdapter(private val children : List<Day>) : RecyclerView.Adapter<ChildAdapter.ChildHolder>() {


    inner class ChildHolder(val binding : ChildItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        return ChildHolder(ChildItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {
        holder.binding.ciValue.text = children[position].the_temp.toString()+"°"
    }

    override fun getItemCount(): Int {
        return children.size
    }
}