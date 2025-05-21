package com.example.MD.data.mapper

import com.example.MD.Domain.GenreModel
import com.example.MD.Domain.MovieDetailsModel
import com.example.MD.data.entity.DTO.GenreDTO
import com.example.MD.data.entity.DTO.MovieDetailsModelDTO

class MovieDetailsModelDTOMapper(private val mapper:GenreDTOMapper, private val mapperReview:ReviewModelDTOMapper) {
    fun map(dto: MovieDetailsModelDTO): MovieDetailsModel {
        return with(dto) {
            MovieDetailsModel(
                id = id,
                name = name,
                poster = poster,
                year = year,
                country = country,
                genres = genres?.map{mapper.map(it)},
                reviews = reviews?.map{mapperReview.map(it)},
                time = time,
                tagline = tagline,
                description = description,
                director = director,
                budget = budget,
                fees = fees,
                ageLimit = ageLimit
            )
        }
    }
}