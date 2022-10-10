package com.zaclippard.androidaccelerator2022.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.zaclippard.androidaccelerator2022.models.Source

class SourceConverter {
    @TypeConverter
    fun toSource(json: String) =
        Gson().fromJson(json, Source::class.java)

    @TypeConverter
    fun fromSource(source: Source) =
        Gson().toJson(source)
}
