package com.healvimaginer.watchfilm.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("results")
    val results: List<FilmResponse>
)