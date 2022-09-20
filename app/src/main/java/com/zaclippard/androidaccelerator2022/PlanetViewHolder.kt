package com.zaclippard.androidaccelerator2022

import androidx.recyclerview.widget.RecyclerView
import com.zaclippard.androidaccelerator2022.databinding.PlanetViewBinding
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.views.PlanetView

class PlanetViewHolder(
    private val planetView: PlanetView,
//    private val binding: PlanetViewBinding,
) : RecyclerView.ViewHolder(planetView) {
//) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(planet: Planet, onDeleteTapped: () -> Unit, onPlanetTapped: () -> Unit) {
        planetView.setData(planet, onDeleteTapped)
//        binding.nameTextView.text = planet.name
        planetView.setOnClickListener { onPlanetTapped() }
    }

}
