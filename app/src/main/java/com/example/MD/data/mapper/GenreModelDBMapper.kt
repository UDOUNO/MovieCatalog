package com.example.MD.data.mapper

import com.example.MD.Domain.GenreListModel
import com.example.MD.Domain.GenreModel
import com.example.MD.data.entity.DB.GenreForDBModelDB
import com.example.MD.data.entity.DB.GenreListDBModel

class GenreModelDBMapper {
    fun map(dbModel: GenreForDBModelDB): GenreModel {
        return with(dbModel){
            GenreModel(
                id = id,
                name = name
            )
        }
    }
    fun map(dbModel: GenreModel): GenreForDBModelDB {
        return with(dbModel){
            GenreForDBModelDB(
                id = id,
                name = name
            )
        }
    }
}