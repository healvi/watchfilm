package com.healvimaginer.watchfilm.core.data.source.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.core.data.source.local.entity.FavoriteTvEntity
import com.healvimaginer.watchfilm.core.data.source.local.room.dao.FavoriteTvDao

@Database(entities = [FavoriteTvEntity::class], version = 1, exportSchema = false)
abstract class FavoriteTvDatabase : RoomDatabase() {
    abstract fun favoriteTvDao(): FavoriteTvDao
}