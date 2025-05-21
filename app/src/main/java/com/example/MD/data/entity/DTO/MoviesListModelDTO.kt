package com.example.MD.data.entity.DTO

import com.example.MD.Domain.MoviesListModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesListModelDTO(
    @SerializedName("movies")
    val movies:List<MovieElementModelDTO>? = null
)
