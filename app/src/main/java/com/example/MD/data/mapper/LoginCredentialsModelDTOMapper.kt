package com.example.MD.data.mapper

import com.example.MD.Domain.GenreModel
import com.example.MD.Domain.LoginCredentialsModel
import com.example.MD.data.entity.DTO.GenreDTO
import com.example.MD.data.entity.DTO.LoginCredentialsModelDTO

class LoginCredentialsModelDTOMapper {
    fun map(model:LoginCredentialsModel ): LoginCredentialsModelDTO {
        return with(model){
            LoginCredentialsModelDTO(username = username, password = password)
        }
    }
}