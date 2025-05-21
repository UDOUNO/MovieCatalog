package com.example.MD.data.entity.DTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PageInfoModelDTO(
    @SerializedName("pageSize")
    val pageSize:Int,
    @SerializedName("pageCount")
    val pageCount:Int,
    @SerializedName("currentPage")
    val currentPage:Int
)

