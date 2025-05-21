package com.example.MD.data.repository

import com.example.MD.Domain.GenreListModel
import com.example.MD.Domain.GenreModel
import com.example.MD.Domain.LocalData
import com.example.MD.data.mapper.GenreListDBModelMapper
import com.example.MD.data.mapper.GenreModelDBMapper

class LocalDataSourceImpl(
    private val genresList:GenreListDBModelMapper,
    private val genre:GenreModelDBMapper
): LocalData{
    override suspend fun getGenres(): GenreListModel {
        return genresList.map(AppDataBase.dataBase.localDataSourceDao().getGenres())
    }

    override suspend fun addToFav(genreModel: GenreModel) {
//        DataBase.dataBase.localDataSourceDao().addToFav(genre.map(genreModel))
    }
}