package com.zaclippard.androidaccelerator2022.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "sources")
data class Source(
    @PrimaryKey
    val name: String,
    val id: String?,
) : Parcelable
