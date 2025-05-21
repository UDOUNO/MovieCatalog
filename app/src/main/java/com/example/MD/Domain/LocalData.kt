package com.example.MD.Domain

interface LocalData {
    suspend fun getGenres():GenreListModel
    suspend fun addToFav(genreModel: GenreModel)
}