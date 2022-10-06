package com.zaclippard.androidaccelerator2022.singleactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zaclippard.androidaccelerator2022.App
import com.zaclippard.androidaccelerator2022.PlanetRecyclerAdapter
import com.zaclippard.androidaccelerator2022.databinding.FragmentPlanetListBinding
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.networking.buildStarWarsApiService
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import com.zaclippard.androidaccelerator2022.viewmodels.PlanetListViewModel

class PlanetListFragment : Fragment() {

    private lateinit var binding: FragmentPlanetListBinding

    // Using the lazy viewModels method is a light wrapper
    // around fetching the viewmodel from ViewModelProviders
    private val viewModel: PlanetListViewModel by viewModels {
        PlanetListViewModel.Factory(planetRepo = App.planetRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanetListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.planets.observe(viewLifecycleOwner) { planetsResult ->
            when (planetsResult) {
                is CustomResult.Success -> {
                    showPlanetList(planetsResult.value)
                }
                is CustomResult.Failure -> {
                    showError()
                }
            }
        }

        binding.flowButton.setOnClickListener {
            val action = PlanetListFragmentDirections.actionPlanetListFragmentToFlowExampleFragment()
            findNavController().navigate(action)
        }
    }

    private fun showPlanetList(planets: List<Planet>) {
        val planetAdapter = PlanetRecyclerAdapter(planets) { planet ->
            val directions = PlanetListFragmentDirections.actionPlanetListFragmentToPlanetDetailsFragment(planet)
            findNavController().navigate(directions)
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
        Snackbar.make(binding.root, "There was an error fetching planet data!", Toast.LENGTH_LONG).show()
    }
}
