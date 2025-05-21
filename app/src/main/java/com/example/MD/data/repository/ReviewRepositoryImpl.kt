package com.example.MD.data.repository

import com.example.MD.Domain.FavoriteMovies
import com.example.MD.Domain.MoviesListModel
import com.example.MD.Domain.Review
import com.example.MD.Domain.ReviewModifyModel
import com.example.MD.data.Retrofit
import com.example.MD.data.datasource.remote.FavoriteMoviesApiService
import com.example.MD.data.datasource.remote.ReviewApiService
import com.example.MD.data.mapper.MoviesListModelDTOMapper
import com.example.MD.data.mapper.ReviewModifyModelDTOMapper

class ReviewRepositoryImpl (
    private val reviewModify: ReviewModifyModelDTOMapper
) : Review{
    override suspend fun reviewAdd(movieId: String, review: ReviewModifyModel) {
        Retrofit.Reviews.reviewAdd(movieId,reviewModify.map(review))
    }

    override suspend fun reviewEdit(movieId: String, id: String, review: ReviewModifyModel) {
        Retrofit.Reviews.reviewEdit(movieId,id,reviewModify.map(review))
    }

    override suspend fun reviewDelete(movieId: String, id: String) {
        Retrofit.Reviews.reviewDelete(movieId,id)
    }
}