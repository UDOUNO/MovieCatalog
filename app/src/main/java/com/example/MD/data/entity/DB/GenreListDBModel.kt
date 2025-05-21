package com.example.MD.data.entity.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
class GenreListDBModel(
    @PrimaryKey(autoGenerate = true)
    val genres:List<GenreForDBModelDB>
)