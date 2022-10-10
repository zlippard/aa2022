package com.zaclippard.androidaccelerator2022.dto

import android.os.Parcelable
import com.zaclippard.androidaccelerator2022.models.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDto(
    val title: String,
    val author: String?,
    val description: String?,
    val url: String?,
    val source: Source,
) : Parcelable
