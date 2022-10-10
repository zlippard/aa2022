package com.zaclippard.androidaccelerator2022.networking

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private fun buildRetrofit(baseUrl: String) =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

fun buildStarWarsApiService() = buildRetrofit("https://swapi.dev")
    .create(StarWarsApiService::class.java)

fun buildNewsApiService() = buildRetrofit("https://newsapi.org")
    .create(NewsApiService::class.java)


