package com.example.MD.Domain

class RegisterUseCase(private val authRepository:Auth) {
    suspend operator fun invoke(user:UserRegisterModel){
        authRepository.register(user)
    }
}