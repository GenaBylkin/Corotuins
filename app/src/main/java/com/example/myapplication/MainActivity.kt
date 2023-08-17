package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatCallback
import androidx.core.view.isVisible
import com.example.myapplication.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonDownload.setOnClickListener {
            loadData()
        }
    }



    private fun loadData() {
        binding.progressBar.isVisible = true
        binding.buttonDownload.isEnabled = false
        loadCity{
            binding.tvCity.text = it
            loadTemperature(it) {
                binding.tvTemperature.text = it.toString()
                binding.progressBar.isVisible = false
                binding.buttonDownload.isEnabled = true
            }

        }

    }

    private fun loadCity(callback: (String) -> Unit) {
        thread {
            Thread.sleep(5000)
            callback.invoke("Moscow")
        }

    }

    private fun loadTemperature(city: String, callback: (Int) -> Unit) {
        thread {
            Toast.makeText(
                this,
                "Load temperature from $city",
                Toast.LENGTH_LONG
            ).show()
            Thread.sleep(5000)
            callback.invoke(17)
        }
    }


}