package com.example.MD.data.mapper

import com.example.MD.Domain.UserShortModel
import com.example.MD.data.entity.DTO.UserShortModelDTO

class UserShortModelDTOMapper {
    fun map(dto: UserShortModelDTO): UserShortModel {
        return with(dto) {
            UserShortModel(
                userId = userId,
                nickName = nickName,
                avatar = avatar
            )
        }
    }

    fun map(dto: UserShortModel): UserShortModelDTO {
        return with(dto) {
            UserShortModelDTO(
                userId = userId,
                nickName = nickName,
                avatar = avatar
            )
        }
    }
}