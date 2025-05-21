package com.example.MD.Domain

class AddFavGenreUseCase(private val localDataRep: LocalData) {
    suspend operator fun invoke(genre: GenreModel) {
        localDataRep.addToFav(genre)
    }
}