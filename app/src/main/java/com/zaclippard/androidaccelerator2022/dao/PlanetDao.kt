package com.zaclippard.androidaccelerator2022.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.zaclippard.androidaccelerator2022.models.Planet

@Dao
interface PlanetDao {
    @Query("SELECT * FROM planets")
    suspend fun getPlanets(): List<Planet>

    @Insert(onConflict = REPLACE)
    suspend fun addPlanets(planets: List<Planet>)

    @Query("SELECT * FROM planets WHERE name LIKE :search")
    suspend fun searchPlanets(search: String): List<Planet>
}
