package com.example.MD.data.entity.DTO

import com.google.gson.annotations.SerializedName

data class LoginCredentialsModelDTO(
    @SerializedName("username")
    val username:String? = null,
    @SerializedName("password")
    val password:String? = null
)
