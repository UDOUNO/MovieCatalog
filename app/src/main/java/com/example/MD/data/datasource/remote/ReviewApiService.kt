package com.example.MD.data.datasource.remote

import com.example.MD.data.entity.DTO.ReviewModifyModelDTO
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewApiService {
    @POST("movie/{movieId}/review/add")
    suspend fun reviewAdd(@Path("movieId")movieId:String, @Body review: ReviewModifyModelDTO): ResponseBody
    @PUT("movie/[movieId]/review/{id}/edit")
    suspend fun reviewEdit(@Path("movieId")movieId: String,@Path("id")id:String, @Body review: ReviewModifyModelDTO): ResponseBody
    @DELETE("movie/[movieId]/review/{id}/edit")
    suspend fun reviewDelete(@Path("movieId")movieId: String,@Path("id")id:String): ResponseBody
}