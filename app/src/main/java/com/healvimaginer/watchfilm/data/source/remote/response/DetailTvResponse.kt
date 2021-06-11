package com.healvimaginer.watchfilm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTvResponse(
    @field:SerializedName("results")
    val results: TvResponse
)