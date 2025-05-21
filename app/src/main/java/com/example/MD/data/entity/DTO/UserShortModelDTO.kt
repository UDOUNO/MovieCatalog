package com.example.MD.data.entity.DTO
import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName

@Serializable
data class UserShortModelDTO(
    @SerializedName("userId")
    val userId:String,
    @SerializedName("nickname")
    val nickName:String? = null,
    @SerializedName("avatar")
    val avatar:String?
)
