package com.example.MD.data.entity.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreForDBModelDB(
    @PrimaryKey
    val id:String,
    @ColumnInfo(name = "name")
    val name:String? = null
)
