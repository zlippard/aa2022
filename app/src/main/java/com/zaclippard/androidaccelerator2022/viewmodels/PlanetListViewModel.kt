package com.zaclippard.androidaccelerator2022.viewmodels

import androidx.lifecycle.*
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.repo.PlanetRepo
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PlanetListViewModel(
    private val planetRepo: PlanetRepo,
) : ViewModel() {

    class Factory(
        private val planetRepo: PlanetRepo,
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlanetListViewModel(planetRepo) as T
        }
    }

    init {
        viewModelScope.launch(IO) {
            planetRepo
                .getPlanets()
                .onEach { newPlanets ->
                    _planets.postValue(newPlanets)
                }
                .collect()
        }
    }

    fun searchPlanets(search: String) {
        viewModelScope.launch(IO) {
            val filteredPlanets = planetRepo.searchPlanets("%$search%")
            _planets.postValue(CustomResult.Success(filteredPlanets))
        }
    }

    private val _planets = MutableLiveData<CustomResult<List<Planet>>>()
    val planets: LiveData<CustomResult<List<Planet>>> = _planets

}
