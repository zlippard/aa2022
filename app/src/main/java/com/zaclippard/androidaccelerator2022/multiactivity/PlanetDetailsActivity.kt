package com.zaclippard.androidaccelerator2022.multiactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.zaclippard.androidaccelerator2022.databinding.ActivityPlanetDetailsBinding
import com.zaclippard.androidaccelerator2022.databinding.FragmentPlanetDetailsBinding
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.singleactivity.PlanetDetailsFragmentArgs

class PlanetDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanetDetailsBinding

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlanetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val planet = intent.getParcelableExtra<Planet>(INTENT_EXTRA_PLANET)
        binding.planetDataTextView.text = planet?.toString()
    }

    companion object {
        const val INTENT_EXTRA_PLANET = "planet"
    }
}
