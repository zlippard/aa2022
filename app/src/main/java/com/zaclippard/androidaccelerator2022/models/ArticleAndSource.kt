package com.zaclippard.androidaccelerator2022.models

import androidx.room.Embedded
import androidx.room.Relation

data class ArticleAndSource(
    @Embedded
    val article: Article,
    @Relation(parentColumn = "source_name", entityColumn = "name")
    val source: Source,
)
