package com.zaclippard.androidaccelerator2022.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "planets")
data class Planet(
    // Commented out to support the Star Wars API data
//    val name: String,
//    val moons: List<Moon>,
//    val ringType: RingType,
    @PrimaryKey val name: String,
    val climate: String,
    val terrain: String,
    val diameter: String,
    val population: String,
) : Parcelable

