package com.example.MD.data.mapper

import com.example.MD.Domain.MoviesPagedListModel
import com.example.MD.data.entity.DTO.MoviesPagedListModelDTO

class MoviesPagedListModelDTOMapper(private val mapper:MovieElementModelDTOMapper, private val mapperPage:PageListModelDTOMapper) {
    fun map(dto:MoviesPagedListModelDTO):MoviesPagedListModel{
        return with(dto){
            MoviesPagedListModel(
                movies = movies?.map{mapper.map(it)},
                pageInfo = mapperPage.map(pageInfo)
            )
        }
    }
}