package com.zaclippard.androidaccelerator2022.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zaclippard.androidaccelerator2022.models.Article
import com.zaclippard.androidaccelerator2022.models.ArticleAndSource

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<ArticleAndSource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articles: List<Article>)

    @Query("DELETE FROM articles")
    suspend fun deleteArticles()
}
