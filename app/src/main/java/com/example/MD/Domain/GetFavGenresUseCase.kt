package com.example.MD.Domain

class GetFavGenresUseCase(private val localDataRep:LocalData) {
    suspend operator fun invoke():GenreListModel{
        return localDataRep.getGenres()
    }
}