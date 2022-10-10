package com.zaclippard.androidaccelerator2022.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zaclippard.androidaccelerator2022.models.Source

@Dao
interface SourceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSource(source: Source)

    @Query("DELETE FROM sources")
    suspend fun deleteSources()
}
