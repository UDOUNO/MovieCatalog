package com.example.MD.Domain

class LogoutUseCase(private val authRepository:Auth) {
    suspend operator fun invoke(){
        authRepository.logout()
    }
}