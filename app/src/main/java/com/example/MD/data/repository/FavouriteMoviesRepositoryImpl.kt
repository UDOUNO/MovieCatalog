package com.example.MD.data.repository

import android.util.Log
import androidx.core.content.edit
import com.example.MD.Domain.FavoriteMovies
import com.example.MD.Domain.MovieElementModel
import com.example.MD.Domain.MoviesListModel
import com.example.MD.SingUpActivity
import com.example.MD.data.Retrofit
import com.example.MD.data.datasource.remote.FavoriteMoviesApiService
import com.example.MD.data.entity.DTO.MoviesListModelDTO
import com.example.MD.data.mapper.MoviesListModelDTOMapper
import kotlinx.coroutines.flow.callbackFlow

class FavouriteMoviesRepositoryImpl(
    private val moviesList:MoviesListModelDTOMapper
) : FavoriteMovies{
    override suspend fun getFavorites(): MoviesListModel {
        return moviesList.map(Retrofit.FavouriteMovies.getFavorites())
    }

    override suspend fun addFavorites(id: String) {
        try{
            Retrofit.FavouriteMovies.addFavorites(id)
        }
        catch(e:Exception){
            Log.e("","bad request")
        }
    }

    override suspend fun deleteFavorites(id: String) {
        Retrofit.FavouriteMovies.deleteFavorites(id)
    }
}