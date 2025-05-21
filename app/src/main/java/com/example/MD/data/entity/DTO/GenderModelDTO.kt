package com.example.MD.data.entity.DTO

import com.example.MD.Domain.GenderModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class GenderModelDTO(
    @SerializedName("gender")
    val gender: Int
)
