package com.healvimaginer.watchfilm.data.source.remote.response


data class TvResponse(
    var contentId:String,
    var title: String?,
    val name: String?,
    var overview: String?,
    var popularity: Float?,
    var poster_path: String?,
    var backdrop_path: String?,
    var vote_average: Float?,
    var release_date: String?,
    val first_air_date: String?
)