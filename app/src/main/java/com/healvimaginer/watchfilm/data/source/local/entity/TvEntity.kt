package com.healvimaginer.watchfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv")
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "contentId")
    var contentId:String,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "description")
    var description:String,

    @ColumnInfo(name = "kreator")
    var kreator:String,

    @ColumnInfo(name = "rilis")
    var rilis : String,

    @ColumnInfo(name = "image")
    var image:String,

    @ColumnInfo(name = "network")
    var network :String,

    @ColumnInfo(name = "status")
    var status : String,


)