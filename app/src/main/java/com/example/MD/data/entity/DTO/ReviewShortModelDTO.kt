package com.example.MD.data.entity.DTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewShortModelDTO(
    @SerializedName("id")
    val id:String,
    @SerializedName("rating")
    val rating:Int
)
