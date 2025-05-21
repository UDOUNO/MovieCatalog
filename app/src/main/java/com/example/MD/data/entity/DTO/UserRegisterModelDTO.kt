package com.example.MD.data.entity.DTO

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterModelDTO(
    @SerializedName("username")
    val username:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("email")
    val email:String,
    @SerializedName("birthday")
    val birthday:String,
    @SerializedName("gender")
    val gender:Int
)
