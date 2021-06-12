package com.healvimaginer.watchfilm.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvResponse(
    @field:SerializedName("id")
    var contentId:String,

    @field:SerializedName("popularity")
    val popularity: Float?,

    @field:SerializedName("vote_count")
    val vote_count: Int?,

    @field:SerializedName("vote_average")
    val vote_average: Float?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("first_air_date")
    val first_air_date: String?,

    @field:SerializedName("original_language")
    val original_language: String?,

    @field:SerializedName("original_title")
    val original_title: String?,

    @field:SerializedName("backdrop_path")
    val backdrop_path: String?,

    @field:SerializedName("adult")
    val adult: Boolean?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("poster_path")
    val poster_path: String?
): Parcelable