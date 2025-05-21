package com.example.MD.Domain

data class MoviesPagedListModel(
    val movies:List<MovieElementModel>?,
    val pageInfo:PageInfoModel
)
