package com.example.MD.Domain

interface Movie {
    suspend fun getMovies(page:Int):MoviesPagedListModel
    suspend fun getDetails(id:String):MovieDetailsModel
}