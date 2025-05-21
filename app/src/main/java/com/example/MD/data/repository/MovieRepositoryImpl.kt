package com.example.MD.data.repository

import com.example.MD.Domain.Movie
import com.example.MD.Domain.MovieDetailsModel
import com.example.MD.Domain.MoviesPagedListModel
import com.example.MD.data.Retrofit
import com.example.MD.data.mapper.MovieDetailsModelDTOMapper
import com.example.MD.data.mapper.MoviesPagedListModelDTOMapper

class MovieRepositoryImpl (
    private val moviesDetailsList: MovieDetailsModelDTOMapper,
    private val moviesPagedList: MoviesPagedListModelDTOMapper
) : Movie {
    override suspend fun getMovies(page: Int): MoviesPagedListModel {
        return moviesPagedList.map(Retrofit.Movie.getMovies(page))
    }

    override suspend fun getDetails(id: String): MovieDetailsModel {
        return moviesDetailsList.map(Retrofit.Movie.getDetails(id))
    }
}