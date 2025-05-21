package com.example.MD.addons

class FilmItem(val iD:String, var rate:Float, val isLiked:Boolean, val uri:String?) {
    var id:String = iD
    var rating:Float = rate
    var liked:Boolean = isLiked
    var url: String? = uri
}