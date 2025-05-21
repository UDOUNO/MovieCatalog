package com.example.MD.data.entity.DTO

import com.example.MD.Domain.GenreModel
import com.example.MD.Domain.ReviewShortModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieElementModelDTO(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("poster")
    val poster:String? = null,
    @SerializedName("year")
    val year:Int,
    @SerializedName("country")
    val country:String? = null,
    @SerializedName("genres")
    val genres:List<GenreDTO>? = null,
    @SerializedName("reviews")
    val reviews:List<ReviewShortModelDTO>? = null
)
