package com.healvimaginer.watchfilm.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailTvResponse(
    @field:SerializedName("results")
    val results: TvResponse
)