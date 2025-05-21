package com.example.MD.data.datasource

import AppDataBase
import android.content.Context
import androidx.room.Room

object Dependencies {
    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val appDatabase: AppDataBase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .createFromAsset("room_article.db")
            .build()
    }
}