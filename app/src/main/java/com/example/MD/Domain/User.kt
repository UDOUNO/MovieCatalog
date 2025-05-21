package com.example.MD.Domain

interface User {
    suspend fun getProfile():ProfileModel
    suspend fun refactorProfile(user:ProfileModel)
}