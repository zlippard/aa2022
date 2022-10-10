package com.zaclippard.androidaccelerator2022

import android.app.Application
import com.zaclippard.androidaccelerator2022.networking.buildNewsApiService
import com.zaclippard.androidaccelerator2022.networking.buildStarWarsApiService
import com.zaclippard.androidaccelerator2022.repo.ArticleRepo
import com.zaclippard.androidaccelerator2022.repo.ArticleRepoImpl
import com.zaclippard.androidaccelerator2022.repo.PlanetRepo
import com.zaclippard.androidaccelerator2022.repo.PlanetRepoImpl

class App : Application() {
    companion object {
        private lateinit var instance: App
        private val starWarsDatabase: StarWarsDatabase by lazy {
            StarWarsDatabase.buildDatabase(instance)
        }
        private val newsDatabase: NewsDatabase by lazy {
            NewsDatabase.buildDatabase(instance)
        }
        private val starWarsApiService by lazy {
            buildStarWarsApiService()
        }
        private val newsApiService by lazy {
            buildNewsApiService()
        }

        val planetRepo: PlanetRepo by lazy {
            PlanetRepoImpl(starWarsDatabase.planetDao(), starWarsApiService)
        }
        val articleRepo: ArticleRepo by lazy {
            ArticleRepoImpl(newsDatabase.articleDao(), newsDatabase.sourceDao(), newsApiService)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
