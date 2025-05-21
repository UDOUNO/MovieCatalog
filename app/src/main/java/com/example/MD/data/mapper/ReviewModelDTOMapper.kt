package com.example.MD.data.mapper

import com.example.MD.Domain.ProfileModel
import com.example.MD.Domain.ReviewModel
import com.example.MD.Domain.UserShortModel
import com.example.MD.data.entity.DTO.ProfileModelDTO
import com.example.MD.data.entity.DTO.ReviewModelDTO

class ReviewModelDTOMapper(private val mapper: UserShortModelDTOMapper) {
    fun map(dto: ReviewModelDTO): ReviewModel {
        return with(dto) {
            ReviewModel(
                id = id,
                rating = rating,
                reviewText = reviewText,
                isAnonymous = isAnonymous,
                createDateTime = createDateTime,
                author = mapper.map(author)
            )
        }
    }

    fun map(dto: ReviewModel): ReviewModelDTO {
        return with(dto) {
            ReviewModelDTO(
                id = id,
                rating = rating,
                reviewText = reviewText,
                isAnonymous = isAnonymous,
                createDateTime = createDateTime,
                author = mapper.map(author)
            )
        }
    }
}