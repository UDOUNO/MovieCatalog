package com.example.MD.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.MD.data.entity.DB.GenreForDBModelDB
import com.example.MD.data.entity.DB.GenreListDBModel

@Dao
interface LocalDataSourceDao {
    @Query("SELECT * FROM genres")
    suspend fun getGenres():GenreListDBModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFav(genreDbModel:GenreForDBModelDB)
}