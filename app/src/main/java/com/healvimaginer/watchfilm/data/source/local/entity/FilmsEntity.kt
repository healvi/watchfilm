package com.healvimaginer.watchfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class FilmsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "contentId")
    var contentId:String,
    @ColumnInfo(name = "title")
    var title: String?,
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
    @ColumnInfo(name = "release_date")
    var release_date: String?,
)