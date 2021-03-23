package com.example.proba.main.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.proba.R
import com.example.proba.databinding.InputFragmentBinding
import com.example.proba.databinding.ShowFragmentBinding
import com.example.proba.main.model.TetaMenza
import com.example.proba.main.view_model.CustomViewModel

class ShowFragment : Fragment() {

    private val viewModel : CustomViewModel by activityViewModels()
    private var _binding : ShowFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : ArrayAdapter<TetaMenza>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShowFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        adapter= ArrayAdapter(requireContext(), R.layout.custom_list_item)
        binding.listView.adapter = adapter
        viewModel.tete.observe(viewLifecycleOwner, Observer {
            adapter.addAll(it)
            adapter.notifyDataSetChanged()
        })
        return view
    }
}