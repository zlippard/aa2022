package com.zaclippard.androidaccelerator2022

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.views.PlanetView

class PlanetRecyclerAdapter(
    planetList: List<Planet>,
    private val onPlanetTapped: (Planet) -> Unit,
) : Adapter<PlanetViewHolder>() {

    private val planets = planetList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        Log.e("RecyclerView", "onCreateViewHolder called")
        val planetView = PlanetView(parent.context)
        planetView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        return PlanetViewHolder(planetView)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        Log.e("RecyclerView", "onBindViewHolder called at position: $position")
        holder.bindData(
            planet = planets[position],
            onDeleteTapped = {
                removePlanetAtIndex(holder.absoluteAdapterPosition)
            },
            onPlanetTapped = {
                onPlanetTapped(planets[holder.absoluteAdapterPosition])
            }
        )
    }

    override fun getItemCount(): Int {
        return planets.size
    }

    fun addPlanet(index: Int, planet: Planet) {
        planets.add(index, planet)
        notifyItemInserted(index)
    }

    private fun removePlanetAtIndex(index: Int) {
        planets.removeAt(index)
        notifyItemRemoved(index)
    }

}
