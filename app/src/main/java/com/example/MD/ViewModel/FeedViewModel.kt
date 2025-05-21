package com.example.MD.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MD.Domain.AddFavGenreUseCase
import com.example.MD.Domain.AddFavoritesUseCase
import com.example.MD.Domain.GenreListModel
import com.example.MD.Domain.GenreModel
import com.example.MD.Domain.GetFavGenresUseCase
import com.example.MD.Domain.MovieElementModel
import com.example.MD.Domain.MoviesPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel(
    private val addFavourites: AddFavoritesUseCase,
    private val getMovie: MoviesPageUseCase,
    private val getGenres: GetFavGenresUseCase,
    private val addGenres:AddFavGenreUseCase
) : ViewModel() {
    private val _movie = MutableStateFlow<MovieElementModel?>(null)
    val movie: StateFlow<MovieElementModel?> = _movie
    private val _movieBack = MutableStateFlow<MovieElementModel?>(null)
    val movieBack: StateFlow<MovieElementModel?> = _movieBack
    private var movies: List<MovieElementModel>? = null
    private val _genre = MutableStateFlow<List<GenreModel>>(emptyList())
    val genre:StateFlow<List<GenreModel>> = _genre
    fun addFavor(id: String) {
        viewModelScope.launch { addFavourites(id) }
    }

    fun getPageRand(): Int {
        var page: Int = (1..5).random()
        return page
    }

    fun getIndexRand(): Int {
        var index: Int = (0..5).random()
        return index
    }

    fun onMovieSwap() {
        viewModelScope.launch {
            if (movies == null) {
                movies = getMovie(getPageRand()).movies!!
                _movie.value = movies!![getIndexRand()]
                _movieBack.value = movies!![getIndexRand()]
            } else {
                _movie.value = _movieBack.value
                movies = getMovie(getPageRand()).movies!!
                _movieBack.value = movies!![getIndexRand()]
            }
        }
    }

    fun addFavGenre(genre:GenreModel){
        viewModelScope.launch {
            addGenres(genre)
        }
    }

    fun getFav(){
        viewModelScope.launch {
            _genre.value = getGenres().genres
        }
    }
}
