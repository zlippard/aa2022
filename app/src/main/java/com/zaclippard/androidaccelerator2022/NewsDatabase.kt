package com.zaclippard.androidaccelerator2022

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zaclippard.androidaccelerator2022.converters.SourceConverter
import com.zaclippard.androidaccelerator2022.dao.ArticleDao
import com.zaclippard.androidaccelerator2022.models.Article
import com.zaclippard.androidaccelerator2022.models.Source

private const val DATABASE_VERSION = 1

@Database(entities = [Article::class, Source::class], version = DATABASE_VERSION)
@TypeConverters(SourceConverter::class)
abstract class NewsDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "News"

        fun buildDatabase(context: Context): NewsDatabase {
            return Room.databaseBuilder(
                context,
                NewsDatabase::class.java,
                DATABASE_NAME,
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun articleDao(): ArticleDao
}

