package com.example.MD.data.mapper

import com.example.MD.Domain.GenderModel
import com.example.MD.Domain.ReviewShortModel
import com.example.MD.Domain.UserRegisterModel
import com.example.MD.data.entity.DTO.ReviewShortModelDTO
import com.example.MD.data.entity.DTO.UserRegisterModelDTO

class UserRegisterModelDTOMapper(private val mapper:GenderModelDTOMapper) {
    fun map(dto: UserRegisterModelDTO): UserRegisterModel {
        return with(dto) {
            UserRegisterModel(
                username = username,
                name = name,
                password = password,
                email = email,
                birthday = birthday,
                gender = GenderModel.entries[gender]
            )
        }
    }
    fun map(model:UserRegisterModel):UserRegisterModelDTO{
        return with(model) {
            UserRegisterModelDTO(
                username = username,
                name = name,
                password = password,
                email = email,
                birthday = birthday,
                gender = mapper.map(gender)
            )
        }
    }
}