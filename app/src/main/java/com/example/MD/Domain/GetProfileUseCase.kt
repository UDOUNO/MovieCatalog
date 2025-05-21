package com.example.MD.Domain

import retrofit2.Call

class GetProfileUseCase (private val profile:User){
    suspend operator fun invoke():ProfileModel {
        return profile.getProfile()
    }
}