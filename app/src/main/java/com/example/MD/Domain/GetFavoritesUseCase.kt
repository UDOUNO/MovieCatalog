package com.example.MD.Domain

class GetFavoritesUseCase (private val favoriteMoviesRepository:FavoriteMovies){
    suspend operator fun invoke():MoviesListModel{
        return favoriteMoviesRepository.getFavorites()
    }
}