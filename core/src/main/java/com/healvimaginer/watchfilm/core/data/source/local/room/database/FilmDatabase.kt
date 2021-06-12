package com.healvimaginer.watchfilm.core.data.source.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.healvimaginer.watchfilm.core.data.source.local.entity.FilmsEntity
import com.healvimaginer.watchfilm.core.data.source.local.room.dao.FilmDao


@Database(entities = [FilmsEntity::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun FilmDao(): FilmDao
}