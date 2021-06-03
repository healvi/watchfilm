package com.healvimaginer.watchfilm.data.source.remote.response

data class FilmResponse(
    var contentId:String,
    var title:String,
    var description:String,
    var director:String,
    var rilis : String,
    var image:String,
    var anggaran : String,
    var pendapatan :String
)