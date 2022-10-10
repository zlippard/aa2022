package com.zaclippard.androidaccelerator2022.repo

import android.util.Log
import com.zaclippard.androidaccelerator2022.dao.ArticleDao
import com.zaclippard.androidaccelerator2022.dao.SourceDao
import com.zaclippard.androidaccelerator2022.models.Article
import com.zaclippard.androidaccelerator2022.models.ArticleAndSource
import com.zaclippard.androidaccelerator2022.networking.NewsApiService
import com.zaclippard.androidaccelerator2022.utils.CustomResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleRepoImpl(
    private val articleDao: ArticleDao,
    private val sourceDao: SourceDao,
    private val newsApiService: NewsApiService,
) : ArticleRepo {
    override fun getArticles(): Flow<CustomResult<List<Article>>> {
//    override fun getArticles(): Flow<CustomResult<List<Article>>> {
            return flow {
            val articlesFromLocalDb = articleDao.getArticles()

            emit(CustomResult.Success(articlesFromLocalDb))

            Log.i(TAG, "articlesFromLocalDb size = ${articlesFromLocalDb.size}")

            try {
                val articlesFromNetwork = newsApiService
                    .getArticles()
                    .articles

//                val articleAndSourceList = articlesFromNetwork
//                    .map { articleDto ->
//                        val article = Article(
//                            articleDto.title,
//                            articleDto.author,
//                            articleDto.description,
//                            articleDto.url,
//                            articleDto.source.name,
//                        )
//                        ArticleAndSource(article, articleDto.source)
//                    }
                emit(CustomResult.Success(articlesFromNetwork))
                if (articlesFromNetwork.isNotEmpty()) {
                    articleDao.deleteArticles()
                    sourceDao.deleteSources()
                    articlesFromNetwork.forEach { article ->
                        sourceDao.addSource(article.source)
                    }
                    articleDao.addArticles(articlesFromNetwork)
                }
            } catch (e: Exception) {
                //emit(CustomResult.Failure(e.message ?: "Unknown Failure"))
                Log.e(TAG, e.toString())
            }
        }
    }

    companion object {
        private const val TAG = "ArticleRepoImpl"
    }
}
