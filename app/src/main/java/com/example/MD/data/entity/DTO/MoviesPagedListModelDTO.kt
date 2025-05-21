package com.example.MD.data.entity.DTO

import com.example.MD.Domain.MoviesListModel
import com.example.MD.Domain.PageInfoModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesPagedListModelDTO(
    @SerializedName("movies")
    val movies:List<MovieElementModelDTO>? = null,
    @SerializedName("pageInfo")
    val pageInfo: PageInfoModelDTO
)
