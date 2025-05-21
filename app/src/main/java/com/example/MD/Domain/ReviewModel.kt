package com.example.MD.Domain

data class ReviewModel(
    val id:String,
    val rating:Int,
    val reviewText:String?,
    val isAnonymous:Boolean,
    val createDateTime:String,
    val author:UserShortModel
)
