package com.healvimaginer.watchfilm.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "FavoriteFilm")
@Parcelize
data class FavoriteFilmEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "contentId")
    var contentId:String,

    @ColumnInfo(name = "anggaran")
    var anggaran:String,

    @ColumnInfo(name = "description")
    var description:String,

    @ColumnInfo(name = "director")
    var director:String,

    @ColumnInfo(name = "image")
    var image:String,

    @ColumnInfo(name = "pendapatan")
    var pendapatan:String,

    @ColumnInfo(name = "rilis")
    var rilis:String,

    @ColumnInfo(name = "title")
    var title:String,

) : Parcelable