package com.example.MD.data.mapper

import com.example.MD.Domain.GenreListModel
import com.example.MD.data.entity.DB.GenreListDBModel

class GenreListDBModelMapper(val mapper:GenreModelDBMapper) {
    fun map(dbModel:GenreListDBModel):GenreListModel{
        return with(dbModel){
            GenreListModel(
                genres = genres.map{mapper.map(it)}
            )
        }
    }
    fun map(dbModel:GenreListModel):GenreListDBModel{
        return with(dbModel){
            GenreListDBModel(
                genres = genres.map{mapper.map(it)}
            )
        }
    }
}