package com.example.MD.data.datasource.remote

import com.example.MD.SingUpActivity
import com.example.MD.data.entity.DTO.MoviesListModelDTO
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoriteMoviesApiService {
    @GET("favorites")
    suspend fun getFavorites(): MoviesListModelDTO
    @POST("favorites/{id}/add")
    suspend fun addFavorites(@Path("id") id:String): Response<ResponseBody>
    @DELETE("favorites/{id}/delete")
    suspend fun deleteFavorites(@Path("id")id:String): Response<ResponseBody>
}