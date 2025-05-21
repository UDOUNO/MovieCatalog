package com.example.MD.Domain

class AddReviewUseCase  (private val reviewRepository:Review){
    suspend operator fun invoke(id:String, review:ReviewModifyModel){
        reviewRepository.reviewAdd(id, review)
    }
}