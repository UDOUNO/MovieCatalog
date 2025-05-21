package com.example.MD.data.datasource.remote

import com.example.MD.data.entity.DTO.LoginCredentialsModelDTO
import com.example.MD.data.entity.DTO.UserRegisterModelDTO
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApiService {
    @POST("account/register")
    suspend fun register(@Body user: UserRegisterModelDTO): ResponseBody
    @POST("account/login")
    suspend fun login(@Body user: LoginCredentialsModelDTO):ResponseBody
    @POST("account/logout")
    suspend fun logout():ResponseBody
}