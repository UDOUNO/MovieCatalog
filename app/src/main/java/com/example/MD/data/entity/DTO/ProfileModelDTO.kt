package com.example.MD.data.entity.DTO

import com.example.MD.Domain.GenderModel
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileModelDTO(
    @SerializedName("id")
    val id:String,
    @SerializedName("nickName")
    val nickname:String? = null,
    @SerializedName("email")
    val email:String,
    @SerializedName("avatarLink")
    val avatarLink:String? = null,
    @SerializedName("name")
    val name:String,
    @SerializedName("birthDate")
    val birthDate:String,
    @SerializedName("gender")
    val gender: Int
)
