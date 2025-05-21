package com.example.MD.data.mapper

import com.example.MD.Domain.ReviewModel
import com.example.MD.Domain.ReviewModifyModel
import com.example.MD.data.entity.DTO.ReviewModelDTO
import com.example.MD.data.entity.DTO.ReviewModifyModelDTO

class ReviewModifyModelDTOMapper {
    fun map(dto: ReviewModifyModel): ReviewModifyModelDTO {
        return with(dto) {
            ReviewModifyModelDTO(
                reviewText = reviewText,
                rating = rating,
                isAnonymous = isAnonymous
            )
        }
    }
}