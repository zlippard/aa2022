package com.zaclippard.androidaccelerator2022.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "articles",
)
data class Article(
    @PrimaryKey
    val title: String,
    val author: String?,
    val description: String?,
    val url: String?,
    val source: Source,
//    @ColumnInfo(name = "source_name") val sourceName: String,
) : Parcelable
