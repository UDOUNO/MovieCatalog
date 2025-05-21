package com.example.MD.data.mapper

import com.example.MD.Domain.GenreModel
import com.example.MD.data.entity.DTO.GenreDTO

class GenreDTOMapper {
    fun map(dto: GenreDTO): GenreModel{
        return with(dto){
            GenreModel(id = id, name=name)
        }
    }
    fun map(dto: GenreModel): GenreDTO{
        return with(dto){
            GenreDTO(id = id, name=name)
        }
    }
}