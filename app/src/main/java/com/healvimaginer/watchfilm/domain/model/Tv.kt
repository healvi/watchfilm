package com.healvimaginer.watchfilm.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tv (
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
        ) : Parcelable