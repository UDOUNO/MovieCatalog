package com.example.MD.data.mapper

import com.example.MD.Domain.GenderModel
import com.example.MD.Domain.GenreModel
import com.example.MD.data.entity.DTO.GenderModelDTO
import com.example.MD.data.entity.DTO.GenreDTO

class GenderModelDTOMapper {
    fun map(dto: GenderModelDTO): GenderModel {
        return with(dto){
            GenderModel.entries[dto.gender]
        }
    }
    fun map(model: GenderModel): Int {
        return with(model){
           model.ordinal
        }
    }
}