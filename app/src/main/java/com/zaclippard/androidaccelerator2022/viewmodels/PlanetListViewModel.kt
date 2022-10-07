package com.zaclippard.androidaccelerator2022.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.zaclippard.androidaccelerator2022.models.Planet
import com.zaclippard.androidaccelerator2022.networking.StarWarsApiService
import com.zaclippard.androidaccelerator2022.repo.PlanetRepo
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import kotlinx.coroutines.Dispatchers
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

    val planets: LiveData<CustomResult<List<Planet>>> =
        planetRepo.getPlanets().asLiveData()

}
