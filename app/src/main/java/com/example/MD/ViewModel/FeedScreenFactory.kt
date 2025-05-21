package com.example.MD.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.MD.Domain.AddFavGenreUseCase
import com.example.MD.Domain.AddFavoritesUseCase
import com.example.MD.Domain.GetFavGenresUseCase
import com.example.MD.Domain.MoviesPageUseCase

class FeedScreenFactory (
    private val addFav:AddFavoritesUseCase,
    private val getMoviesPage:MoviesPageUseCase,
    private val getGenre: GetFavGenresUseCase,
    private val addGenre: AddFavGenreUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedViewModel::class.java)) {
            return FeedViewModel(addFavourites=addFav, getMovie = getMoviesPage, getGenres = getGenre, addGenres = addGenre) as T
        }
        throw IllegalArgumentException("UnLuck")
    }
}