package com.zaclippard.androidaccelerator2022.networking

import com.zaclippard.androidaccelerator2022.models.GetPlanetsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface StarWarsApiService {
    @GET("/api/planets")
    fun getPlanets(): Call<GetPlanetsResponse>


    @GET("/api/planets")
    suspend fun getPlanetsViaSuspend(): Response<GetPlanetsResponse>
}


