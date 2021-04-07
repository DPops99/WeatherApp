package com.example.proba.main.first_fragment


import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import coil.load
import coil.transform.CircleCropTransformation
import com.example.proba.R
import com.example.proba.databinding.InputFragmentBinding
import com.example.proba.main.model.TetaMenza
import com.example.proba.main.model.enums.Job_Position
import com.example.proba.main.model.enums.Workplace
import com.example.proba.main.view_model.CustomViewModel
import com.example.proba.network.repository.Repository
import kotlinx.coroutines.launch

class InputFragment : Fragment() {

    private val viewModel : CustomViewModel by activityViewModels()
    private var _binding : InputFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : ArrayAdapter<Workplace>
    private lateinit var workplace: Workplace
    private lateinit var job_position: Job_Position

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InputFragmentBinding.inflate(inflater,container,false)
        val view = binding.root

        setView()
        setSpinners()
        setListeners()

        viewModel.viewModelScope.launch {
           Log.d("back",Repository().getDay(44418,"2013/4/27")[0].toString())
        }


        return view
    }


    fun setView(){



        binding.firstName.textView.text = getString(R.string.first_name).toUpperCase()

        binding.lastName.textView.text = getString(R.string.last_name).toUpperCase()

        binding.age.textView.text = getString(R.string.age).toUpperCase()
        binding.age.editText.inputType = InputType.TYPE_CLASS_NUMBER

        binding.OIB.textView.text = getString(R.string.oib).toUpperCase()
        binding.OIB.editText.inputType=InputType.TYPE_CLASS_NUMBER

        binding.email.textView.text = getString(R.string.email).toUpperCase()
        binding.email.editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        binding.address.textView.text = getString(R.string.address).toUpperCase()
    }

    fun setSpinners(){
        //setting up workplace spinner
        binding.spinnerWorkplace.adapter =ArrayAdapter<Workplace>(requireContext(),android.R.layout.simple_spinner_dropdown_item, Workplace.values() )
        binding.spinnerWorkplace.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                val x = parent?.getItemAtPosition(0)
                if(x is Workplace)
                    workplace = x
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val x = parent?.getItemAtPosition(position)
                if(x is Workplace)
                    workplace = x
            }
        }

        //setting up Job_Positoin spinner
        binding.spinnerJobPosition.adapter =ArrayAdapter<Job_Position>(requireContext(),android.R.layout.simple_spinner_dropdown_item, Job_Position.values() )
        binding.spinnerJobPosition.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                val x = parent?.getItemAtPosition(0)
                if(x is Job_Position)
                    job_position = x
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val x = parent?.getItemAtPosition(position)
                if(x is Job_Position)
                    job_position = x
            }
        }

    }

    fun setListeners(){

        binding.btnSave.setOnClickListener(){
            if(binding.firstName.editText.text.toString().equals("") || binding.lastName.editText.text.toString().equals("") || binding.OIB.editText.text.toString().equals("") ||
                binding.age.editText.text.toString().equals("") || binding.email.editText.text.toString().equals("") || binding.address.editText.text.toString().equals("")){

                Toast.makeText(getContext(),"Please fill all the fields before saving",Toast.LENGTH_LONG).show();
            }
            else if (binding.email.editText.text.toString().contains("@")){
                var checked_gender : String
                var profile_img = ""
                when(binding.gender.checkedRadioButtonId){
                    R.id.radio_btn_female -> {checked_gender = getString(R.string.female)
                                                profile_img = "https://static.thenounproject.com/png/17239-200.png"}
                    R.id.radio_btn_male -> {checked_gender = getString(R.string.male)
                                                profile_img = "https://static.thenounproject.com/png/17241-200.png"}
                    else -> {checked_gender = getString(R.string.female)
                                profile_img = "https://static.thenounproject.com/png/17239-200.png"}
                }

                var checked_shift : String

                when(binding.jobShift.checkedRadioButtonId){
                    R.id.radio_first -> checked_shift = getString(R.string.first)
                    R.id.radio_second -> checked_shift = getString(R.string.second)
                    else -> checked_shift = getString(R.string.second)
                }



                viewModel.addTeta(TetaMenza(binding.firstName.editText.text.toString(),binding.lastName.editText.text.toString(),workplace,
                    binding.age.editText.text.toString().toInt(),binding.OIB.editText.text.toString().toInt(), checked_gender,
                    job_position, checked_shift, binding.email.editText.text.toString(), binding.address.editText.text.toString(),profile_img))
                clearEditTextFields()
            }
            else{
                Toast.makeText(getContext(),"Email is not properly entered",Toast.LENGTH_LONG).show();
            }
        }
    }

    fun clearEditTextFields(){
        binding.firstName.editText.text.clear()
        binding.lastName.editText.text.clear()
        binding.OIB.editText.text.clear()
        binding.age.editText.text.clear()
        binding.email.editText.text.clear()
        binding.address.editText.text.clear()
    }
}