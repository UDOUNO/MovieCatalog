package com.example.MD.Domain

class EditReviewUseCase (private val reviewRepository:Review){
    suspend operator fun invoke(movieId:String, id:String, review:ReviewModifyModel){
        reviewRepository.reviewEdit(movieId,id,review)
    }
}