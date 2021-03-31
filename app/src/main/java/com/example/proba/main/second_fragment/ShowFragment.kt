package com.example.proba.main.second_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proba.R
import com.example.proba.databinding.InputFragmentBinding
import com.example.proba.databinding.ShowFragmentBinding
import com.example.proba.main.model.TetaMenza
import com.example.proba.main.second_fragment.adapter.TetaAdapter
import com.example.proba.main.view_model.CustomViewModel
import com.example.proba.singleTeta.SingleTetaActivity
import com.google.android.material.snackbar.Snackbar

class ShowFragment : Fragment(), TetaAdapter.OnItemClickListener {

    private val viewModel : CustomViewModel by activityViewModels()
    private var _binding : ShowFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : TetaAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShowFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        adapter = TetaAdapter(viewModel.tete.value!!,requireContext(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.tete.observe(viewLifecycleOwner, Observer {
            adapter.tete = it
            adapter.notifyDataSetChanged()

//            Snackbar.make(view,getString(R.string.list_updated),Snackbar.LENGTH_LONG).setAction(R.string.undo, View.OnClickListener {
//                viewModel.deleteTeta(adapter.tete.last())
//            }).show()
        })
        return view
    }

    override fun onItemClick(position: Int) {
//        val bundle : Bundle = Bundle()
//        bundle.putSerializable(getString(R.string.teta_menza_bundle),adapter.tete[position])
        val intent : Intent = Intent(this.context, SingleTetaActivity::class.java)
        intent.putExtra(getString(R.string.teta_menza_bundle),adapter.tete[position])
        startActivity(intent)
    }
}