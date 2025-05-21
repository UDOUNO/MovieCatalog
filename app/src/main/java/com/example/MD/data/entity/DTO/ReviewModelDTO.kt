package com.example.MD.data.entity.DTO

import com.example.MD.Domain.UserShortModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewModelDTO(
    @SerializedName("id")
    val id:String,
    @SerializedName("rating")
    val rating:Int,
    @SerializedName("reviewText")
    val reviewText:String? = null,
    @SerializedName("isAnonymous")
    val isAnonymous:Boolean,
    @SerializedName("createDateTime")
    val createDateTime:String,
    @SerializedName("author")
    val author: UserShortModelDTO
)
