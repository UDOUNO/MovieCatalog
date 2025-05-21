package com.example.MD.Domain

import retrofit2.Call

class DetailsUseCase (private val movieRepository:Movie){
    suspend operator fun invoke(id:String) : MovieDetailsModel {
        return movieRepository.getDetails(id)
    }
}