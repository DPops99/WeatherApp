package com.example.proba.singleCity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.databinding.ChildItemBinding
import com.example.proba.singleCity.model.ChildModel

class ChildAdapter(private val children : List<ChildModel>) : RecyclerView.Adapter<ChildAdapter.ChildHolder>() {


    inner class ChildHolder(val binding : ChildItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        return ChildHolder(ChildItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return children.size
    }
}