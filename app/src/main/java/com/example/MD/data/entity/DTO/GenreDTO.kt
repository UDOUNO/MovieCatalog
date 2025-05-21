package com.example.MD.data.entity.DTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GenreDTO(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name:String? = null
)
