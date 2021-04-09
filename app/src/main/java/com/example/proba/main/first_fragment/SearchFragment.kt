package com.example.proba.main.first_fragment


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proba.databinding.SearchFragmentBinding
import com.example.proba.main.first_fragment.adapter.SearchAdapter
import com.example.proba.main.view_model.ApiViewModel
import com.example.proba.network.model.Day
import com.example.proba.network.model.Search
import com.example.proba.singleCity.SingleCityActivity
import java.io.Serializable

class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {

    private val apiViewModel : ApiViewModel by activityViewModels()
    private var _binding : SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : SearchAdapter
    private var current_adapter_position : Int? = null
    val day_bundle = "DAY_BUNDLE"
    val city_bundle = "CITY_BUNDLE"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater,container,false)
        val view = binding.root


        setView()
//
//        apiViewModel.viewModelScope.launch {
//           Log.d("back",Repository().getDay(44418,"2013/4/27")[0].toString())
//        }


        return view
    }


    fun setView(){

        binding.searchRecyclerView.layoutManager =  LinearLayoutManager(requireContext())
        adapter = SearchAdapter(ArrayList<Search>(),this.requireContext(), this)
        binding.searchRecyclerView.adapter = adapter
        apiViewModel.api_search.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.cities = it
                adapter.notifyDataSetChanged()
            }
        })

        apiViewModel.api_city.observe(viewLifecycleOwner, Observer {
            if(it != null){
                it.consolidated_weather?.get(0)?.applicable_date?.let { it1 -> apiViewModel.get_api_day(adapter.cities[current_adapter_position!!].woeid!!, it1)}
//                val intent : Intent = Intent(this.context, SingleCityActivity::class.java)
//                intent.putExtra(search_fragment_bundle, it)
//                startActivity(intent)
            }
        })


        apiViewModel.api_day.observe(viewLifecycleOwner, Observer {
            it?.let {
                val intent : Intent = Intent(this.context, SingleCityActivity::class.java)
                Log.d("BEFORE_LIFTOF",(it as Serializable).toString())
                intent.putExtra(day_bundle, it as Serializable)
                intent.putExtra(city_bundle, apiViewModel.api_city.value)


                startActivity(intent)
            }
        })


        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null) {
                    Log.d("BACK_API","got from search view")
                    apiViewModel.get_api_search(query)
                }
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }


    override fun onItemClick(position: Int) {
//        val bundle : Bundle = Bundle()
//        bundle.putSerializable(getString(R.string.teta_menza_bundle),adapter.tete[position])
        if (adapter.cities[position].woeid != null) {
            current_adapter_position = position
            apiViewModel.get_api_city(adapter.cities[position].woeid!!)
        }

//        intent.putExtra(getString(R.string.teta_menza_bundle),adapter.tete[position])

    }


//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun isNetworkConnected() : Boolean{
//
//        val conn_manager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val active_network = conn_manager.activeNetwork
//
//    }

}