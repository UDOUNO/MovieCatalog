package com.example.MD.data.datasource.remote

import com.example.MD.data.entity.DTO.MovieDetailsModelDTO
import com.example.MD.data.entity.DTO.MoviesPagedListModelDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("movies/{page}")
    suspend fun getMovies(@Path("page")page:Int):MoviesPagedListModelDTO
    @GET("movies/details/{id}")
    suspend fun getDetails(@Path("id")id:String): MovieDetailsModelDTO
}