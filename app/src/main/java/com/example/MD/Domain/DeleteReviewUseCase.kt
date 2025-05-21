package com.example.MD.Domain

class DeleteReviewUseCase(private val reviewRepository: Review) {
    suspend operator fun invoke(movieId: String, id: String) {
        reviewRepository.reviewDelete(movieId, id)
    }
}