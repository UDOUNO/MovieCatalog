package com.example.MD.addons

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.MD.data.datasource.local.LocalDataSourceDao
import com.example.MD.data.entity.DB.GenreForDBModelDB
import com.example.MD.data.entity.DB.GenreListDBModel

@Database(entities = [GenreForDBModelDB::class,GenreListDBModel::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun localDataSourceDao():LocalDataSourceDao
}