package com.example.proba.main.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.proba.R
import com.example.proba.databinding.ShowFragmentBinding
import com.example.proba.main.first_fragment.adapter.SearchAdapter
import com.example.proba.network.model.City
import com.example.proba.room.viewmodel.RoomFactory
import com.example.proba.room.viewmodel.RoomViewModel


class ShowFragment : Fragment(), SearchAdapter.OnItemClickListener, SearchAdapter.OnItemLongClickListener{

    private var _binding : ShowFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SearchAdapter
    private lateinit var roomViewModel : RoomViewModel
    private lateinit var roomViewModelFactory : RoomFactory
    private lateinit var current_city : City
    private val itemTouchHelper by lazy {

        val simpleItemTouchCallback =
                object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,0 ){
                    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {

                        val from = viewHolder.adapterPosition
                        val to = target.adapterPosition

                        adapter.moveItem(from,to)
                        adapter.notifyItemMoved(from, to)
                        return true

                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    }

                }
        ItemTouchHelper(simpleItemTouchCallback)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShowFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        roomViewModelFactory = RoomFactory(requireContext())
        roomViewModel = ViewModelProvider(this,roomViewModelFactory).get(RoomViewModel::class.java)
        setView()
        setListeners()
        return view
    }


    fun setView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SearchAdapter(ArrayList<City>(),this.requireContext(), null, this)
        binding.recyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(null)

        roomViewModel.fav_cities.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                adapter.cities = it as ArrayList<City>
                adapter.notifyDataSetChanged()
            }
        })
    }

    fun setListeners(){
        binding.showEdit.setOnClickListener{
            if(binding.showCal.visibility == View.VISIBLE){
                binding.showCal.visibility=View.INVISIBLE
                binding.showEdit.load(R.drawable.ic_baseline_check_24)
                itemTouchHelper.attachToRecyclerView(binding.recyclerView)
                adapter = SearchAdapter(adapter.cities,this.requireContext(), this, this)

            }
            else{
                binding.showCal.visibility=View.VISIBLE
                binding.showEdit.load(R.drawable.ic_icon_1)
                itemTouchHelper.attachToRecyclerView(null)
                adapter = SearchAdapter(adapter.cities,this.requireContext(), null, this)
            }
            binding.recyclerView.adapter = adapter
        }
    }

    override fun onItemClick(position: Int, isFav : Boolean) {
        current_city = adapter.cities[position]
        current_city.favorite = isFav
        roomViewModel.saveAndGetFavCity(current_city)
    }

    override fun onItemLongClick(position: Int) {

    }
}