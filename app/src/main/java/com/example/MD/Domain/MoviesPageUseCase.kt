package com.example.MD.Domain

import retrofit2.Call

class MoviesPageUseCase (private val movieRepository:Movie){
    suspend operator fun invoke(page:Int) : MoviesPagedListModel{
        return movieRepository.getMovies(page)
    }
}