package com.example.bottomnavigationtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.bottomnavigationtesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host_fragment_container) }
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pageSelect()

    }
    private fun pageSelect(){
        binding.bottomNavigationMain?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.firstFragment -> {
                    navController.navigate(R.id.firstFragment)
                }
                R.id.secondFragment -> {
                    navController.navigate(R.id.secondFragment)
                }
                R.id.thirdFragment -> {
                    navController.navigate(R.id.thirdFragment)
                }
            }
            true
        }
    }
}