package com.zaclippard.androidaccelerator2022.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Planet(
    // Commented out to support the Star Wars API data
//    val name: String,
//    val moons: List<Moon>,
//    val ringType: RingType,
    val name: String,
    val climate: String,
    val terrain: String,
    val diameter: String,
    val population: String,
) : Parcelable

