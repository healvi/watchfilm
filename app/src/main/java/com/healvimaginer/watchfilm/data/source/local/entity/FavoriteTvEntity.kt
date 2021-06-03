package com.healvimaginer.watchfilm.data.source.local.entity

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

    @ColumnInfo(name = "netword")
    var network:String,

    @ColumnInfo(name = "description")
    var description:String,

    @ColumnInfo(name = "kreator")
    var kreator:String,

    @ColumnInfo(name = "image")
    var image:String,

    @ColumnInfo(name = "status")
    var status:String,

    @ColumnInfo(name = "rilis")
    var rilis:String,

    @ColumnInfo(name = "title")
    var title:String,

) : Parcelable