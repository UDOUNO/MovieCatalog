package com.example.MD.Domain

data class MovieElementModel(
    val id:String,
    val name:String?,
    val poster:String?,
    val year:Int,
    val country:String?,
    val genres:List<GenreModel>?,
    val reviews:List<ReviewShortModel>?
)
