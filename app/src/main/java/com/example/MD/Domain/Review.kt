package com.example.MD.Domain

interface Review {
    suspend fun reviewAdd(movieId:String, review:ReviewModifyModel)
    suspend fun reviewEdit(movieId: String,id:String, review: ReviewModifyModel)
    suspend fun reviewDelete(movieId: String,id:String)
}