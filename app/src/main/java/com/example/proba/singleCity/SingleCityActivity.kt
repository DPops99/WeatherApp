package com.example.proba.singleCity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.R
import com.example.proba.databinding.ActivitySingleCityBinding
import com.example.proba.databinding.ActivitySingleTetaBinding
import com.example.proba.singleCity.adapter.ParentAdapter
import com.example.proba.singleCity.model.ChildModel
import com.example.proba.singleCity.model.ParentModel

class SingleCityActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySingleCityBinding

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
        binding.scRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = ParentAdapter(getDummyData(2))
        }
    }

    private fun getDummyData(count : Int) : List<ParentModel>{
        val parents = mutableListOf<ParentModel>()
        repeat(count){
            parents.add(ParentModel("danas",getChildren(7)))
        }
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