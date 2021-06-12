package com.healvimaginer.watchfilm.core.data.source.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.core.data.source.local.entity.TvEntity
import com.healvimaginer.watchfilm.core.data.source.local.room.dao.TvDao

@Database(entities = [TvEntity::class], version = 1, exportSchema = false)
abstract class TvDatabase : RoomDatabase() {
    abstract fun TvDao(): TvDao
}