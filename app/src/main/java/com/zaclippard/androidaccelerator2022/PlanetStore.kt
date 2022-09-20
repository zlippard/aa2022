package com.zaclippard.androidaccelerator2022

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zaclippard.androidaccelerator2022.models.Planet

class PlanetStore(context: Context) {
    private val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    private val gson = Gson()

    fun storePlanets(planets: List<Planet>) {
        val planetsJson = gson.toJson(planets)
        sharedPrefs.edit().putString(PREFS_KEY_PLANETS, planetsJson).apply()
    }

    fun fetchPlanets(): List<Planet> {
        val planetsJson = sharedPrefs.getString(PREFS_KEY_PLANETS, "")
        val planetListTypeToken = object : TypeToken<List<Planet>>() { }
        return gson.fromJson(planetsJson, planetListTypeToken.type)
    }

    companion object {
        private const val PREFS_KEY_PLANETS = "planets"
    }
}
