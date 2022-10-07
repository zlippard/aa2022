package com.zaclippard.androidaccelerator2022

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zaclippard.androidaccelerator2022.dao.PlanetDao
import com.zaclippard.androidaccelerator2022.models.Planet

private const val DATABASE_VERSION = 1

@Database(entities = [Planet::class], version = DATABASE_VERSION)
abstract class StarWarsDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "StarWars"

        fun buildDatabase(context: Context): StarWarsDatabase {
            return Room.databaseBuilder(
                context,
                StarWarsDatabase::class.java,
                DATABASE_NAME,
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun planetDao(): PlanetDao
}
