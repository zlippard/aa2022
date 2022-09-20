package com.zaclippard.androidaccelerator2022.singleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zaclippard.androidaccelerator2022.databinding.ActivityMainNavBinding

class MainNavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainNavBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
