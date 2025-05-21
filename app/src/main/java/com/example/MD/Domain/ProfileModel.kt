package com.example.MD.Domain

data class ProfileModel(
    val id:String,
    val nickname:String?,
    val email:String,
    val avatarLink:String?,
    val name:String,
    val birthDate:String,
    val gender:GenderModel
)
