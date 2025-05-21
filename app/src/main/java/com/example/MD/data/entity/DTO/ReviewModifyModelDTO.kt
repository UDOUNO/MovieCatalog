package com.example.MD.data.entity.DTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewModifyModelDTO(
    @SerializedName("reviewText")
    val reviewText:String,
    @SerializedName("rating")
    val rating:Int,
    @SerializedName("isAnonymous")
    val isAnonymous:Boolean
)
