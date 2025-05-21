package com.example.MD.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MD.Domain.GetFavoritesUseCase
import com.example.MD.Domain.MoviesPageUseCase

class MovieScreenViewModelFactory (
    private val getFavor: GetFavoritesUseCase,
    private val getMoviesPage: MoviesPageUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(getFav = getFavor, getMovie = getMoviesPage) as T
        }
        throw IllegalArgumentException("UnLuck")
    }
}