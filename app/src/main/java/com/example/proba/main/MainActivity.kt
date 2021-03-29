package com.example.proba.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.proba.databinding.ActivityMainBinding
import com.example.proba.R
import com.example.proba.main.first_fragment.InputFragment
import com.example.proba.main.second_fragment.ShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenu

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(savedInstanceState==null)
            setCurrentFragment(InputFragment())

        setListeners()

    }

    fun setListeners(){
        binding.navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.input_menu -> setCurrentFragment(InputFragment())
                R.id.show_menu -> setCurrentFragment(ShowFragment())
            }
            true
        }
    }

    fun setCurrentFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentView.id,fragment)
            commit()
        }
    }


}