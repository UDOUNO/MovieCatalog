package com.example.MD.Domain

class AddFavoritesUseCase (private val favoriteMoviesRepository:FavoriteMovies){
    suspend operator fun invoke(id:String){
        favoriteMoviesRepository.addFavorites(id)
    }
}