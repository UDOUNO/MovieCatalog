package com.example.MD.data.entity.DTO

import com.example.MD.Domain.GenreModel
import com.example.MD.Domain.ReviewModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsModelDTO(
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
    val genres: List<GenreDTO>? = null,
    @SerializedName("reviews")
    val reviews: List<ReviewModelDTO>? = null,
    @SerializedName("time")
    val time:Int,
    @SerializedName("tagline")
    val tagline:String? = null,
    @SerializedName("description")
    val description:String? = null,
    @SerializedName("director")
    val director:String? = null,
    @SerializedName("budget")
    val budget:Int? = null,
    @SerializedName("fees")
    val fees:Int? = null,
    @SerializedName("ageLimit")
    val ageLimit:Int
)
