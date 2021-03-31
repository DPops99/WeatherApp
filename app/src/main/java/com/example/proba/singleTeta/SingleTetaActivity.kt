package com.example.proba.singleTeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.proba.R
import com.example.proba.databinding.ActivitySingleTetaBinding
import com.example.proba.main.model.TetaMenza

class SingleTetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleTetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        this.window?.decorView?.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
        }


        binding = ActivitySingleTetaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)




        setView()
    }


    private fun setView(){


        val message= intent.getSerializableExtra(getString(R.string.teta_menza_bundle))

        if (message is TetaMenza) {
            binding.rl2Address.value.text = message.address
            binding.rl2FirstName.value.text = message.first_name
            binding.rl2LastName.value.text = message.last_name
            binding.rl2Oib.value.text = message.OIB.toString()
            binding.rl2Email.value.text = message.email
            binding.rl2JobPosition.value.text = message.job_position.toString()
            binding.rl2JobShift.value.text = message.job_shift
            binding.rl2Age.value.text = message.age.toString()
            binding.rl2Gender.value.text = message.gender
            binding.rl2Workplace.value.text = message.workplace.toString()



            binding.rl2Address.description.text = getString(R.string.address).toUpperCase()
            binding.rl2FirstName.description.text = getString(R.string.first_name).toUpperCase()
            binding.rl2LastName.description.text = getString(R.string.last_name).toUpperCase()
            binding.rl2Oib.description.text = getString(R.string.oib).toUpperCase()
            binding.rl2Email.description.text = getString(R.string.email).toUpperCase()
            binding.rl2JobPosition.description.text = getString(R.string.job_position).toUpperCase()
            binding.rl2JobShift.description.text = getString(R.string.job_shift).toUpperCase()
            binding.rl2Age.description.text = getString(R.string.age).toUpperCase()
            binding.rl2Gender.description.text = getString(R.string.gender).toUpperCase()
            binding.rl2Workplace.description.text = getString(R.string.workplace).toUpperCase()

            binding.tetaFab.load(message.image){
                crossfade(true)
                placeholder(R.drawable.ic_baseline_people_24)
                transformations(CircleCropTransformation())
            }

            binding.tetaFab.scaleType = ImageView.ScaleType.CENTER_CROP
            binding.collapsingToolbarLayout.title = message.first_name.toUpperCase()

        }
    }
}