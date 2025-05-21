package com.example.MD.Domain

class LoginUseCase(private val authRepository:Auth) {
    suspend operator fun invoke(user:LoginCredentialsModel){
        authRepository.login(user)
    }
}