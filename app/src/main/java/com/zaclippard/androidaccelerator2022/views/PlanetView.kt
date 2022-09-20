package com.zaclippard.androidaccelerator2022.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.zaclippard.androidaccelerator2022.R
import com.zaclippard.androidaccelerator2022.databinding.PlanetViewBinding
import com.zaclippard.androidaccelerator2022.models.Moon
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.models.RingType

class PlanetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : CardView(context, attrs, defStyleAttr) {

    private val binding = PlanetViewBinding.inflate(LayoutInflater.from(context), this)

    fun setData(planet: Planet, onDeleteTapped: () -> Unit) {
        setPlanetName(planet.name)
        setPlanetTerrain(planet.terrain)
        setOnDeleteTappedCallback(onDeleteTapped)
    }

    private fun setPlanetName(name: String) {
        binding.nameTextView.text = name
    }

    private fun setPlanetTerrain(terrain: String) {
        binding.terrainTextView.text = terrain
    }

    private fun setOnDeleteTappedCallback(onDeleteTapped: () -> Unit) {
        binding.deletePlanetButton.setOnClickListener { onDeleteTapped() }
    }
}
