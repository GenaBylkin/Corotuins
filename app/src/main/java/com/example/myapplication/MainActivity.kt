package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonDownload.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }

        }
    }



    private suspend fun loadData() {
        binding.progressBar.isVisible = true
        binding.buttonDownload.isEnabled = false
        val city = loadCity()
        binding.tvCity.text = city
        val temper = loadTemperature(city)
        binding.tvTemperature.text = temper.toString()
        binding.progressBar.isVisible = false
        binding.buttonDownload.isEnabled = true
    }

    private suspend fun loadCity() : String {
        delay(5000)
        return "Moscow"
    }

    private suspend fun loadTemperature(city: String): Int {
        Toast.makeText(
            this,
            "Load temperature from $city",
            Toast.LENGTH_LONG
        ).show()
        delay(5000)
        return 17
    }


}