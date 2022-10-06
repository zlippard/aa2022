package com.zaclippard.androidaccelerator2022.multiactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.zaclippard.androidaccelerator2022.App
import com.zaclippard.androidaccelerator2022.PlanetRecyclerAdapter
import com.zaclippard.androidaccelerator2022.databinding.ActivitySecondBinding
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.multiactivity.PlanetDetailsActivity.Companion.INTENT_EXTRA_PLANET
import com.zaclippard.androidaccelerator2022.networking.buildStarWarsApiService
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import com.zaclippard.androidaccelerator2022.viewmodels.PlanetListViewModel

class PlanetListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    // Using the lazy viewModels method is a light wrapper
    // around fetching the viewmodel from ViewModelProviders
    private val viewModel by viewModels<PlanetListViewModel> {
        PlanetListViewModel.Factory(planetRepo = App.planetRepo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Not really a requirement now
        // that we are using a viewmodel! :]
//        Log.i(
//            TAG,
//            "onCreate() called. savedInstanceState[$KEY_TEST] = ${savedInstanceState?.getString(KEY_TEST)}",
//        )

        // Example utilizing coroutine and
        // suspend function for fetching articles
        // from API.
//        lifecycleScope.launch(IO) {
//            val response = buildStarWarsApiService()
//                .getPlanetsViaSuspend()
//
//            if (response.isSuccessful) {
//                showPlanetList(response.body()!!.results)
//            } else {
//                showError()
//            }
//        }

        viewModel.planets.observe(this) { planetsResult ->
            when (planetsResult) {
                is CustomResult.Success -> {
                    showPlanetList(planetsResult.value)
                }
                is CustomResult.Failure -> {
                    showError()
                }
            }
        }
    }

    private fun showPlanetList(planets: List<Planet>) {
        val planetAdapter = PlanetRecyclerAdapter(planets) { planet ->
            val intent = Intent(this, PlanetDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PLANET, planet)
            startActivity(intent)
        }

        binding.planetRecyclerView.run {
            adapter = planetAdapter
        }

        binding.addButton.setOnClickListener {
            planetAdapter.addPlanet(
                index = 1,
                planet = Planet(
                    name = "Tiny planet",
                    climate = "Cold",
                    terrain = "Dusty",
                    diameter = "1",
                    population = "0",
                )
            )
            binding.planetRecyclerView.scrollToPosition(1)
        }
    }

    private fun showError() {
        Snackbar.make(binding.root, "There was an error fetching planet data!", LENGTH_LONG).show()
    }

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

    // Not really a requirement anymore now that
    // we are using a viewmodel! :]
//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putString(KEY_TEST, "data!")
//        super.onSaveInstanceState(outState)
//
//        Log.i(TAG, "onSaveInstanceState() called.")
//    }

    companion object {
        private const val TAG = "SecondActivity"
        private const val KEY_TEST = "test"
    }
}
