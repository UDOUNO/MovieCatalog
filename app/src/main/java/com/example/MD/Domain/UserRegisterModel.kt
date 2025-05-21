package com.example.MD.Domain

data class UserRegisterModel (
    val username:String,
    val name:String,
    val password:String,
    val email:String,
    val birthday:String,
    val gender:GenderModel
)