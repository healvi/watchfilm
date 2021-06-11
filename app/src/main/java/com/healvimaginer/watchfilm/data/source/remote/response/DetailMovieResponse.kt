package com.healvimaginer.watchfilm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @field:SerializedName("results")
    val results: FilmResponse
)