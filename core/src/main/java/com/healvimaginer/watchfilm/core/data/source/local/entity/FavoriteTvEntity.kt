package com.healvimaginer.watchfilm.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "FavoriteTv")
@Parcelize
data class FavoriteTvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "contentId")
    var contentId:String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Float?,
    @ColumnInfo(name = "poster_path")
    var poster_path: String?,
    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String?,
    @ColumnInfo(name = "vote_average")
    var vote_average: Float?,
    @ColumnInfo(name = "first_air_date")
    val first_air_date: String?

) : Parcelable