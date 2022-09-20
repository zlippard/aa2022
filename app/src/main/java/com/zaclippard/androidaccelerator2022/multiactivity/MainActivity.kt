package com.zaclippard.androidaccelerator2022.multiactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zaclippard.androidaccelerator2022.databinding.ActivityMainBinding
import com.zaclippard.androidaccelerator2022.models.Planet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val result = fetchArticles()

        binding.nextButton.setOnClickListener {
            val intent = Intent(this, PlanetListActivity::class.java)
            intent.action = Intent.ACTION_SEND
            startActivity(intent)
        }

        Log.i(TAG, "onCreate() called.")
    }

//    fun fetchArticles(): PlanetsResult<List<Planet>> {
//        try {
//            // fetch from API
//            val list: List<Planet> = listOf()
//
//            return PlanetsResult.Success(list)
//        } catch (e: Exception) {
//            return PlanetsResult.Failure(e)
//        }
//    }

    override fun onStart() {
        super.onStart()

        Log.i(TAG, "onStart() called.")
    }

    override fun onResume() {
        super.onResume()

        Log.i(TAG, "onResume() called.")
    }

    override fun onPause() {
        super.onPause()

        Log.i(TAG, "onPause() called.")
    }

    override fun onStop() {
        super.onStop()

        Log.i(TAG, "onStop() called.")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i(TAG, "onDestroy() called.")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
