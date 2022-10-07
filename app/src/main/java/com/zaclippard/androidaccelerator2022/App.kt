package com.zaclippard.androidaccelerator2022

import android.app.Application
import com.zaclippard.androidaccelerator2022.networking.buildStarWarsApiService
import com.zaclippard.androidaccelerator2022.repo.PlanetRepo
import com.zaclippard.androidaccelerator2022.repo.PlanetRepoImpl

class App : Application() {
    companion object {
        private lateinit var instance: App
        private val database: StarWarsDatabase by lazy {
            StarWarsDatabase.buildDatabase(instance)
        }
        private val starWarsApiService by lazy {
            buildStarWarsApiService()
        }

        val planetRepo: PlanetRepo by lazy {
            PlanetRepoImpl(database.planetDao(), starWarsApiService)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
