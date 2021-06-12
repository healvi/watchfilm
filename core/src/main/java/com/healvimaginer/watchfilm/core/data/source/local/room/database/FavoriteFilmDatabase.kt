package com.healvimaginer.watchfilm.core.data.source.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.core.data.source.local.entity.FavoriteFilmEntity
import com.healvimaginer.watchfilm.core.data.source.local.room.dao.FavoriteFilmDao

@Database(entities = [FavoriteFilmEntity::class], version = 1, exportSchema = false)
abstract class FavoriteFilmDatabase : RoomDatabase() {
    abstract fun favoriteFilmDao(): FavoriteFilmDao
}