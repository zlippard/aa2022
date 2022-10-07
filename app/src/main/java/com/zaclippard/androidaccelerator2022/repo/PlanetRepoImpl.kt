package com.zaclippard.androidaccelerator2022.repo

import android.util.Log
import com.zaclippard.androidaccelerator2022.dao.PlanetDao
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.networking.StarWarsApiService
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlanetRepoImpl(
    private val planetDao: PlanetDao,
    private val starWarsApiService: StarWarsApiService,
) : PlanetRepo {
    override fun getPlanets(): Flow<CustomResult<List<Planet>>> {
        return flow {
            val planetsFromLocalDb = planetDao.getPlanets()

            emit(CustomResult.Success(planetsFromLocalDb))

            Log.i(TAG, "planetsFromLocaDb size = ${planetsFromLocalDb.size}")

            try {
                val planetsFromNetwork = starWarsApiService
                    .getPlanetsViaSuspend()
                    .results

                emit(CustomResult.Success(planetsFromNetwork))

                if (planetsFromNetwork.isNotEmpty()) {
                    planetDao.addPlanets(planetsFromNetwork)
                }
            } catch (e: Exception) {
                //emit(CustomResult.Failure(e.message ?: "Unknown Failure"))
                Log.e(TAG, e.toString())
            }
        }
    }

    companion object {
        private const val TAG = "PlanetRepoImpl"
    }
}
