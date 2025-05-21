package com.example.MD.data.datasource.remote

import com.example.MD.data.entity.DTO.ProfileModelDTO
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApiService {
    @GET("account/profile")
    suspend fun getProfile(): ProfileModelDTO
    @PUT("account/profile")
    suspend fun refactorProfile(@Body user: ProfileModelDTO): ResponseBody
}