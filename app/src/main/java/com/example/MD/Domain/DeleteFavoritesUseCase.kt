package com.example.MD.Domain

class DeleteFavoritesUseCase (private val favoriteMoviesRepository:FavoriteMovies){
    suspend operator fun invoke(id:String){
        favoriteMoviesRepository.deleteFavorites(id)
    }
}