package com.example.MD.data.mapper

import com.example.MD.Domain.MoviesListModel
import com.example.MD.data.entity.DTO.MoviesListModelDTO

class MoviesListModelDTOMapper(val mapper: MovieElementModelDTOMapper) {
    fun map(dto: MoviesListModelDTO): MoviesListModel {
        return with(dto) {
            MoviesListModel(
                movies = movies?.map{mapper.map(it)}
            )
        }
    }
}