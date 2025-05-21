package com.example.MD.Domain

interface FavoriteMovies {
    suspend fun getFavorites():MoviesListModel
    suspend fun addFavorites(id:String)
    suspend fun deleteFavorites(id:String)
}