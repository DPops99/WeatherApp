package com.example.proba.singleCity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.R
import com.example.proba.databinding.ParentRecyclerBinding
import com.example.proba.singleCity.model.ParentModel

class ParentAdapter(private val parents : List<ParentModel>, private val context : Context) : RecyclerView.Adapter<ParentAdapter.ParentHolder>()  {

    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ParentHolder(var binding : ParentRecyclerBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentHolder {
        return ParentHolder(ParentRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ParentHolder, position: Int) {
        holder.binding.prTitle.text = parents[position].title
        holder.binding.prRv.apply {
            layoutManager = LinearLayoutManager(holder.binding.prRv.context,RecyclerView.HORIZONTAL,false)
            if (parents[position].title != context.getString(R.string.today))
                adapter = ChildAdapter(parents[position].children, parents[position].title, context)
            else
                adapter = ChildAdapter(parents[position].children.subList(0,10), parents[position].title, context)
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return parents.size
    }
}