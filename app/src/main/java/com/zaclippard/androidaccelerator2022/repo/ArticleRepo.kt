package com.zaclippard.androidaccelerator2022.repo

import com.zaclippard.androidaccelerator2022.models.ArticleAndSource
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import kotlinx.coroutines.flow.Flow

interface ArticleRepo {
    fun getArticles(): Flow<CustomResult<List<ArticleAndSource>>>
}
