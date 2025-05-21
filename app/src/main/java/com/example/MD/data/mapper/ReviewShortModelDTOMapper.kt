package com.example.MD.data.mapper

import com.example.MD.Domain.ReviewShortModel
import com.example.MD.data.entity.DTO.ReviewShortModelDTO

class ReviewShortModelDTOMapper {
    fun map(dto: ReviewShortModelDTO): ReviewShortModel {
        return with(dto) {
            ReviewShortModel(
                id = id,
                rating = rating
            )
        }
    }
}