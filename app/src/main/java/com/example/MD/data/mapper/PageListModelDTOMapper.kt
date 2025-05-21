package com.example.MD.data.mapper

import com.example.MD.Domain.MoviesPagedListModel
import com.example.MD.Domain.PageInfoModel
import com.example.MD.data.entity.DTO.MoviesPagedListModelDTO
import com.example.MD.data.entity.DTO.PageInfoModelDTO

class PageListModelDTOMapper {
    fun map(dto: PageInfoModelDTO): PageInfoModel {
        return with(dto){
            PageInfoModel(
                pageSize = pageSize,
                pageCount = pageCount,
                currentPage = currentPage
            )
        }
    }
}