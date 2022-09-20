package com.zaclippard.androidaccelerator2022.viewmodels

import android.media.Image
import android.util.Log
import androidx.lifecycle.*
import com.zaclippard.androidaccelerator2022.PlanetStore
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.networking.StarWarsApiService
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetListViewModel(
    private val starWarsApiService: StarWarsApiService,
//    private val planetStore: PlanetStore,
) : ViewModel() {

    class Factory(
        private val starWarsApiService: StarWarsApiService,
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlanetListViewModel(starWarsApiService) as T
        }
    }

    private val _planets = MutableLiveData<CustomResult<List<Planet>>>()
    val planets: LiveData<CustomResult<List<Planet>>> = _planets

    init {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(TAG, "Fetching planets from API...")
            // Reconciling local data and network
            // can be done in a "repository"
            // more on this later. :]
//            val storedPlanets = planetStore.fetchPlanets()
//            val planetsToUse = storedPlanets.ifEmpty {
//                planetStore.storePlanets(planets)
//                planetStore.fetchPlanets()
//            }

            val response = starWarsApiService.getPlanetsViaSuspend()

            // Use postValue instead of value
            // because we are on an IO dispatcher
            // and not on the main thread
            _planets.postValue(if (response.isSuccessful) {
                CustomResult.Success(response.body()!!.results)
            } else {
                CustomResult.Failure(response.errorBody().toString())
            })
        }
    }

    companion object {
        private const val TAG = "PlanetListViewModel"
    }
}
