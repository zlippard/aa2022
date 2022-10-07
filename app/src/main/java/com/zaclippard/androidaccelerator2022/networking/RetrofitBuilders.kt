package com.zaclippard.androidaccelerator2022.networking

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private fun buildStarWarsRetrofit() =
    Retrofit.Builder()
        .baseUrl("https://swapi.dev")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

fun buildStarWarsApiService() = buildStarWarsRetrofit()
    .create(StarWarsApiService::class.java)


