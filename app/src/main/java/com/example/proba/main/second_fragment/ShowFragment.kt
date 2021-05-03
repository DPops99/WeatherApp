package com.example.proba.main.second_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proba.R
import com.example.proba.databinding.ShowFragmentBinding
import com.example.proba.main.first_fragment.adapter.SearchAdapter
import com.example.proba.main.second_fragment.adapter.TetaAdapter
import com.example.proba.main.view_model.ApiViewModel
import com.example.proba.main.view_model.CustomViewModel
import com.example.proba.network.model.City
import com.example.proba.room.database.AppDatabase
import com.example.proba.room.viewmodel.RoomFactory
import com.example.proba.room.viewmodel.RoomViewModel
import com.example.proba.singleTeta.SingleTetaActivity


class ShowFragment : Fragment(), SearchAdapter.OnItemClickListener, SearchAdapter.OnItemLongClickListener{

    private val viewModel : CustomViewModel by activityViewModels()
    private val apiViewModel : ApiViewModel by activityViewModels()
    private var _binding : ShowFragmentBinding? = null
    private val binding get() = _binding!!
//    private lateinit var adapter :TetaAdapter
    private lateinit var adapter: SearchAdapter
    private lateinit var roomDB : AppDatabase
    private lateinit var roomViewModel : RoomViewModel
    private lateinit var roomViewModelFactory : RoomFactory
    private val itemTouchHelper by lazy {

        val simpleItemTouchCallback =
                object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,0 ){
                    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                        val show_adapter = recyclerView.adapter as SearchAdapter
                        val from = viewHolder.adapterPosition
                        val to = target.adapterPosition

//                        show_adapter.moveItem(from, to)
//                        show_adapter.notifyItemMoved(from, to)
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
        roomDB = AppDatabase.getInstance(requireContext())!!
        roomViewModelFactory = RoomFactory(roomDB)
        roomViewModel = ViewModelProvider(this,roomViewModelFactory).get(RoomViewModel::class.java)
        setView()
//        adapter = TetaAdapter(viewModel.tete.value!!,requireContext(), this)
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerView.adapter = adapter
//
//        viewModel.tete.observe(viewLifecycleOwner, Observer {
//            adapter.tete = it
//            adapter.notifyDataSetChanged()
//        })


        return view
    }


    fun setView(){



        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SearchAdapter(ArrayList<City>(),this.requireContext(), this, this)
        binding.recyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        roomViewModel.fav_cities.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                var fav_cities = ArrayList<City>()
                it.forEach {
                    fav_cities.add(it.city)
                }
                adapter.cities = fav_cities
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onItemClick(position: Int, isFav : Boolean) {
//        val bundle : Bundle = Bundle()
//        bundle.putSerializable(getString(R.string.teta_menza_bundle),adapter.tete[position])
//        val intent : Intent = Intent(this.context, SingleTetaActivity::class.java)
//        intent.putExtra(getString(R.string.teta_menza_bundle),adapter.tete[position])
//        startActivity(intent)

    }

    override fun onItemLongClick(position: Int) {

    }
}