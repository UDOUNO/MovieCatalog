package com.example.MD.Domain

interface Auth {
    suspend fun register(user:UserRegisterModel)
    suspend fun login(user:LoginCredentialsModel)
    suspend fun logout()
}
