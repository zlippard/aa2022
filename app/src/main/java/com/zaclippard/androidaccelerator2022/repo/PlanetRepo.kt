package com.zaclippard.androidaccelerator2022.repo

import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import kotlinx.coroutines.flow.Flow

interface PlanetRepo {
    fun getPlanets(): Flow<CustomResult<List<Planet>>>
}
