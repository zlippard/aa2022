package com.zaclippard.androidaccelerator2022.networking

import com.zaclippard.androidaccelerator2022.models.GetArticlesResponse
import retrofit2.http.GET

interface NewsApiService {
    @GET("/v2/top-headlines?apiKey=9d85674c022a498f8eeec67ab41968da&country=US")
    suspend fun getArticles(): GetArticlesResponse
}
