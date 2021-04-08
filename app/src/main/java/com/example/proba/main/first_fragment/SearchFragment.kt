package com.example.proba.main.first_fragment


import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proba.databinding.SearchFragmentBinding
import com.example.proba.main.model.TetaMenza
import com.example.proba.main.model.enums.Job_Position
import com.example.proba.main.model.enums.Workplace
import com.example.proba.main.second_fragment.adapter.SearchAdapter
import com.example.proba.main.view_model.ApiViewModel
import com.example.proba.main.view_model.CustomViewModel
import com.example.proba.network.model.Search
import com.example.proba.network.repository.Repository
import kotlinx.coroutines.launch

class SearchFragment : Fragment(), SearchAdapter.OnItemClickListener {

    private val apiViewModel : ApiViewModel by activityViewModels()
    private var _binding : SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : SearchAdapter

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
        apiViewModel.api_days.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.cities = it
                adapter.notifyDataSetChanged()
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

    }

}