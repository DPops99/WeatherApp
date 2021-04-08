package com.example.proba.main.third_fragment

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.proba.R
import com.example.proba.databinding.SettingsLayoutBinding
import com.example.proba.main.MainActivity
import java.util.*

class SettingsFragment : Fragment() {

    private var _binding : SettingsLayoutBinding? = null
    private val binding get()=_binding!!
    lateinit var locale: Locale

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingsLayoutBinding.inflate(inflater,container,false)
        val view = binding.root

        setView()
        return view
    }


    fun setView(){
        ArrayAdapter.createFromResource(this.requireContext(), R.array.spinner_values, android.R.layout.simple_spinner_dropdown_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.settingsSpinner.adapter = adapter
        }


        binding.settingsBtn.setOnClickListener(View.OnClickListener {
            val item = binding.settingsSpinner.selectedItemPosition
            when (item){
                0 -> setLocale("en", requireContext())
                1 -> setLocale("hr", requireContext())
            }
        })

    }


    fun setLocale(localeName : String, context: Context){
        locale = Locale(localeName)
        val res = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            val localList = LocaleList(locale)
            LocaleList.setDefault(localList)
            conf.setLocales(localList)
        }
        else
            conf.locale = locale

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1)
            context?.createConfigurationContext(conf)
        else
            res.updateConfiguration(conf,dm)

        var intent: Intent = Intent(context,MainActivity::class.java)
        intent.putExtra(getString(R.string.snackbar_bundle),if (localeName=="en") "hr" else "en")

        startActivity(intent)
        this.activity?.finish()
    }
}