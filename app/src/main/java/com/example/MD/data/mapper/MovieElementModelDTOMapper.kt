package com.example.MD.data.mapper

import com.example.MD.Domain.GenreModel
import com.example.MD.Domain.MovieDetailsModel
import com.example.MD.Domain.MovieElementModel
import com.example.MD.Domain.ReviewShortModel
import com.example.MD.data.entity.DTO.MovieDetailsModelDTO
import com.example.MD.data.entity.DTO.MovieElementModelDTO
import com.example.MD.data.entity.DTO.ReviewShortModelDTO

class MovieElementModelDTOMapper(private val mapper:GenreDTOMapper, private val mapperReview:ReviewShortModelDTOMapper) {
    fun map(dto: MovieElementModelDTO): MovieElementModel {
        return with(dto) {
            MovieElementModel(
                id = id,
                name = name,
                poster = poster,
                year = year,
                country = country,
                genres = genres?.map{mapper.map(it)},
                reviews = reviews?.map { mapperReview.map(it) }
            )
        }
    }
}