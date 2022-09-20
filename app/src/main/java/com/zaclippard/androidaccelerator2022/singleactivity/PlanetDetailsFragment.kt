package com.zaclippard.androidaccelerator2022.singleactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.zaclippard.androidaccelerator2022.databinding.FragmentPlanetDetailsBinding

class NewFragment : Fragment() {

}

class PlanetDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPlanetDetailsBinding
    private val args by navArgs<PlanetDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanetDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.planetDataTextView.text = args.planet.toString()
    }
}
