package com.example.MD.Domain

import retrofit2.Call

class EditProfileUseCase(private val profile:User){
    suspend operator fun invoke(user:ProfileModel) {
        return profile.refactorProfile(user)
    }
}