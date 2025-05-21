package com.example.MD.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.MD.Domain.DetailsUseCase
import com.example.MD.Domain.GetFavoritesUseCase
import com.example.MD.Domain.MovieElementModel
import com.example.MD.Domain.MoviesPageUseCase
import com.example.MD.Domain.ReviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMovie: MoviesPageUseCase,
    private val getFav: GetFavoritesUseCase
) : ViewModel() {
    private val _movies = MutableStateFlow<List<MovieElementModel>?>(emptyList())
    val movies: StateFlow<List<MovieElementModel>?> = _movies
    private val _moviesPageOne = MutableStateFlow<List<MovieElementModel>?>(emptyList())
    val moviesPageOne: StateFlow<List<MovieElementModel>?> = _moviesPageOne
    private var moviesPageOneList: List<MovieElementModel>? = emptyList()
    private var moviesList: List<MovieElementModel>? = emptyList()
    private var movieListPaged:List<MovieElementModel>? = emptyList()
    private val _favMovies = MutableStateFlow<List<MovieElementModel>?>(emptyList())
    val favMovies: StateFlow<List<MovieElementModel>?> = _favMovies
    fun getMovies() {
        viewModelScope.launch {
            for (page in 1..5) {
                if (page == 1) {
                    moviesPageOneList = getMovie(1).movies!!
                }
                else{
                    movieListPaged = getMovie(page).movies
                    for(index in 0..<movieListPaged!!.size){
                        Log.e("scope",moviesList!!.size.toString())
                        moviesList = moviesList!! + (movieListPaged!![index])
                } }
            }
            _movies.value = moviesList
            _moviesPageOne.value = moviesPageOneList
        }
    }

    fun getFavors() {
        viewModelScope.launch { _favMovies.value = getFav().movies }
    }
}