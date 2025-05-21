package com.example.MD.data.repository

import com.example.MD.Domain.ProfileModel
import com.example.MD.Domain.Review
import com.example.MD.Domain.ReviewModifyModel
import com.example.MD.Domain.User
import com.example.MD.data.Retrofit
import com.example.MD.data.datasource.remote.ReviewApiService
import com.example.MD.data.datasource.remote.UserApiService
import com.example.MD.data.mapper.ProfileModelDTOMapper
import com.example.MD.data.mapper.ReviewModifyModelDTOMapper

class UserRepositoryImpl (
    private val profile:ProfileModelDTOMapper
) : User {
    override suspend fun getProfile(): ProfileModel {
        return profile.map(Retrofit.User.getProfile())
    }

    override suspend fun refactorProfile(user: ProfileModel) {
        Retrofit.User.refactorProfile(profile.map(user))
    }
}