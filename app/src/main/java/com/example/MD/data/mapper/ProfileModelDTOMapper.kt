package com.example.MD.data.mapper

import com.example.MD.Domain.GenderModel
import com.example.MD.Domain.ProfileModel
import com.example.MD.data.entity.DTO.GenderModelDTO
import com.example.MD.data.entity.DTO.ProfileModelDTO

class ProfileModelDTOMapper (private val mapper:GenderModelDTOMapper){
    fun map(dto: ProfileModelDTO): ProfileModel {
        return with(dto) {
            ProfileModel(
                id = id,
                nickname = nickname,
                email = email,
                avatarLink = avatarLink,
                name = name,
                birthDate = birthDate,
                gender = GenderModel.entries[gender]
            )
        }
    }
    fun map(dto: ProfileModel): ProfileModelDTO {
        return with(dto) {
            ProfileModelDTO(
                id = id,
                nickname = nickname,
                email = email,
                avatarLink = avatarLink,
                name = name,
                birthDate = birthDate,
                gender = mapper.map(gender)
            )
        }
    }
}